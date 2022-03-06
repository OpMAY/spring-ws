package com.util.Encryption;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.model.User;
import com.util.Constant;
import com.util.Time;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

@Service
public class EncryptionService implements Encrypt {
    @Override
    public <T> T getSessionParameter(String token, String key) {
        if (token != null) {
            HashMap<String, Object> hashMap = decryptJWT(token);
            return (T) hashMap.get(key);
        } else {
            return (T) null;
        }
    }

    @Override
    public HashMap<String, Object> decryptJWT(String encryptedJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(encryptedJWT);
            String email = jwt.getClaim("email").asString();
            String signature = jwt.getClaim("signature").asString();
            String grant = jwt.getClaim("grant").asString();
            String version = jwt.getClaim("version").asString();
            String token = jwt.getClaim("token").asString();
            int no = jwt.getClaim("no").asInt();
            String id = jwt.getClaim("id").asString();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("email", email);
            hashMap.put("signature", signature);
            hashMap.put("grant", grant);
            hashMap.put("version", version);
            hashMap.put("token", token);
            hashMap.put("no", no);
            hashMap.put("id", id);
            return hashMap;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String encryptJWT(User user) throws NoSuchAlgorithmException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withExpiresAt(Time.LongTimeStamp())
                .withClaim("version", Constant.VERSION)
                .withClaim("grant", user.getGrant())
                .withClaim("token", user.getAccess_token())
                .withClaim("email", user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("no", user.getNo())
                .withClaim("signature", encryptSHA256("secret"))
                .withIssuer("auth0")
                .sign(algorithm);
    }

    @Override
    public String encryptSHA256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return bytesToHex(md.digest());
    }

    @Override
    public String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * AES256 Encrypt Algorithm
     */
    private final String alg = "AES/CBC/PKCS5Padding";
    private final String key = "0123456789012345";
    private final String iv = key.substring(0, 16); // 16byte

    @Override
    public String encryptAES(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decryptAES(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}

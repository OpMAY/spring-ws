package com.util.Encryption;

import com.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface Encrypt {
    public <T> T getSessionParameter(String token, String key);
    public HashMap<String, Object> decryptJWT(String encryptedJWT);
    public String encryptJWT(User user) throws NoSuchAlgorithmException;
    public String encryptSHA256(String msg) throws NoSuchAlgorithmException;
    public String bytesToHex(byte[] bytes);
    public String encryptAES(String text) throws Exception;
    public String decryptAES(String cipherText) throws Exception;
}

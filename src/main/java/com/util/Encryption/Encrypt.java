package com.util.Encryption;

import com.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface Encrypt {
    public HashMap<String, Object> decodeJWT(String encryptedJWT);
    public String encryptionJWT(User user) throws NoSuchAlgorithmException;
    public String encryptionSHA256(String msg) throws NoSuchAlgorithmException;
    public String bytesToHex(byte[] bytes);
    public String encryptAES(String text) throws Exception;
    public String decryptAES(String cipherText) throws Exception;
}

package com.controller;

import com.util.Encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    public static void main(String[] args) {
        try {
            System.out.println(new EncryptionService().encryptAES("smtp.naver.com", false));
            System.out.println(new EncryptionService().encryptAES("587", false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.model.User;
import com.response.DefaultRes;
import com.response.Message;
import com.response.ResMessage;
import com.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/get-test.do")
    public ModelAndView getTest() {
        log.info("GET");
        return new ModelAndView("test");
    }

    @PostMapping("/post-test1.do")
    public ResponseEntity<String> postTest1(String test) {
        log.info("POST");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }
    @PostMapping("/post-test2.do")
    public ResponseEntity<String> postTest2(@RequestBody String data) {
        log.info("POST");
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(data);
        String val = element.getAsJsonObject().get("test").getAsString();
        System.out.println("val = " + val);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }
    @PostMapping("/post-test3.do")
    public ResponseEntity<String> postTest3(@RequestBody User user) {
        log.info("POST");
        System.out.println("user = " + user);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PutMapping("/put-test.do")
    public ResponseEntity<String> putTest(String test) {
        log.info("PUT");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @PatchMapping("/patch-test.do")
    public ResponseEntity<String> patchTest(String test) {
        log.info("PATCH");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-test.do")
    public ResponseEntity<String> deleteTest(String test) {
        log.info("DELETE");
        System.out.println("test = " + test);
        Message message = new Message();
        message.put("status", true);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }
}

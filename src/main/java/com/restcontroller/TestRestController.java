package com.restcontroller;

import com.response.DefaultRes;
import com.response.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestRestController {
    @RequestMapping("test/loginterceptor")
    public ResponseEntity<String> logInterceptor() {
        Message message = new Message();
        message.put("data", "success");
        return new ResponseEntity(DefaultRes.res(200, message, true), HttpStatus.OK);
    }
}

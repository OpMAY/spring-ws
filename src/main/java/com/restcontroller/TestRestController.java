package com.restcontroller;

import com.api.mail.MailBuilder;
import com.api.mail.MailFooter;
import com.api.mail.MailLogo;
import com.api.mail.MailType;
import com.response.DefaultRes;
import com.response.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final MailBuilder mailBuilder;

    @RequestMapping("test/loginterceptor")
    public ResponseEntity<String> logInterceptor() {
        Message message = new Message();
        message.put("data", "success");
        return new ResponseEntity(DefaultRes.res(200, message, true), HttpStatus.OK);
    }

    @RequestMapping("test/property")
    public ResponseEntity<String> property() throws MessagingException {
        mailBuilder
                .setSession()
                .setTo("zlzldntlr1@naver.com")
                .setMailTitle("Property Test")
                .setMailContent(new MailBuilder().getMailHTML(MailType.PASSWORD, new MailLogo(), new MailFooter(), "dsjfkdsjf"), MailType.PASSWORD).send();
        Message message = new Message()
                .put("data","success").put("zlzl","dntlr");
        return new ResponseEntity(DefaultRes.res(200, message, true), HttpStatus.OK);
    }
}

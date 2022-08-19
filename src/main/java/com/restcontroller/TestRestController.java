package com.restcontroller;

import com.api.LoginAPI;
import com.api.mail.MailBuilder;
import com.api.mail.MailFooter;
import com.api.mail.MailLogo;
import com.api.mail.MailType;
import com.model.User;
import com.model.queue.ServerTokenType;
import com.model.queue.Token;
import com.response.DefaultRes;
import com.response.Message;
import com.service.ServerTokenService;
import com.util.TokenGenerator;
import com.validator.test.Test;
import com.validator.test.TestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestRestController {
    private final LoginAPI loginAPI;
    private final MailBuilder mailBuilder;

    @RequestMapping("test/loginterceptor")
    public ResponseEntity<String> logInterceptor() {
        Message message = new Message();
        message.put("data", "success");
        return new ResponseEntity(DefaultRes.res(HttpStatus.OK, message, true), HttpStatus.OK);
    }

    @RequestMapping("test/property")
    public ResponseEntity<String> property() throws MessagingException {
        mailBuilder
                .setSession()
                .setTo("zlzldntlr1@naver.com")
                .setMailTitle("Property Test")
                .setMailContent(new MailBuilder().getMailHTML(MailType.PASSWORD, new MailLogo(), new MailFooter(), "dsjfkdsjf"), MailType.PASSWORD).send();
        Message message = new Message()
                .put("data", "success");
        return new ResponseEntity(DefaultRes.res(HttpStatus.OK, message, true), HttpStatus.OK);
    }

    @Autowired
    private ServerTokenService serverTokenService;

    @RequestMapping("test/queue")
    public ResponseEntity<String> queue() throws MessagingException {
        String token = TokenGenerator.RandomToken(8);
        token = serverTokenService.put(token, Token.builder()
                .email("zlzldntlr@naver.com")
                .token(token)
                .type(ServerTokenType.REGISTER)
                .reg_datetime(LocalDateTime.now())
                .update_datetime(LocalDateTime.now())
                .build());
        token = TokenGenerator.RandomToken(8);
        token = serverTokenService.put(token, Token.builder()
                .email("zlzldntlr2@naver.com")
                .token(token)
                .type(ServerTokenType.REGISTER)
                .reg_datetime(LocalDateTime.now())
                .update_datetime(LocalDateTime.now())
                .build());
        serverTokenService.removeOldKeys(serverTokenService.getOldKeys());
        Message message = new Message()
                .put("data", "success")
                .put("queue", serverTokenService.toString());
        return new ResponseEntity(DefaultRes.res(HttpStatus.OK, message, true), HttpStatus.OK);
    }

    @RequestMapping(value = "/oauth/callback", method = RequestMethod.GET)
    public ResponseEntity kakaoLoginCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = loginAPI.apiLoginInit(request);
        log.info("user result : {}", user);
        if (user != null) {
            response.sendRedirect("/test/login");
        }
        return new ResponseEntity(DefaultRes.res(HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Validator Test Module
     */
    private final TestValidator validator;

    @RequestMapping(value = "/test/validator", method = RequestMethod.POST)
    public ResponseEntity validatorTest(@RequestBody Test test, BindingResult result) {
        log.info(test.toString());
        validator.validate(test, result);
        if (result.hasErrors()) {
            log.info("Error Accrued -> {}", result.toString());
            return new ResponseEntity(DefaultRes.res(HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultRes.res(HttpStatus.OK), HttpStatus.OK);
    }
}

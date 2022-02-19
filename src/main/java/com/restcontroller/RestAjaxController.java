package com.restcontroller;

import com.model.User;
import com.response.DefaultRes;
import com.response.Message;
import com.response.ResMessage;
import com.response.StatusCode;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
public class RestAjaxController {
    /**
     * Ajax Test
     */
    @CrossOrigin(origins = {"http://localhost:8080", "https://[domain]", "http://[domain]"})
    @ResponseBody
    @RequestMapping(value = "/ajax.do", method = RequestMethod.GET)
    public ResponseEntity<String> ajax() throws JSONException {
        Message message = new Message();
        message.put("test", new User("zlzldntlr@naver.com", "zlzldntlr", "김우식"));
        message.put("test1", "object string test");
        //throw new NullPointerException();
        return new ResponseEntity(
                DefaultRes.res(
                        StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("ajax")
                ), HttpStatus.OK
        );
    }

    /**
     * Properties Test
     */
    @Value("${AWS.ACCESS}")
    private String aws_access;
    @Value("${AWS.SECRET}")
    private String aws_secret;
    @Value("${AWS.BUCKET}")
    private String aws_bucket;

    @ResponseBody
    @RequestMapping(value = "/test/properties.do", method = RequestMethod.GET)
    public ResponseEntity<String> properties() throws JSONException {
        Message message = new Message();
        message.put("access", aws_access);
        message.put("secret", aws_secret);
        message.put("aws_bucket", aws_bucket);
        return new ResponseEntity(
                DefaultRes.res(
                        StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap("ajax")
                ), HttpStatus.OK
        );
    }
}

package com.controller;

import com.model.WSMessage;
import com.model.common.MFile;
import com.response.DefaultRes;
import com.response.Message;
import com.response.ResMessage;
import com.response.StatusCode;
import com.util.FileUploadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final FileUploadUtility fileUploadUtility;

    @GetMapping("/chat")
    public ModelAndView chatTest() {
        return new ModelAndView("socket");
    }

    @MessageMapping("/test/{roomId}")
    @SendTo("/topic/message/{roomId}")
    public WSMessage handle(@DestinationVariable String roomId, WSMessage message) {
        System.out.println("roomId = " + roomId);
        System.out.println("id = " + message.getId());
        System.out.println("message = " + message.getMessage());
        return message;
    }

    @MessageMapping("/binary")
    @SendTo("/topic/message")
//    public WSFile handle(WSFile wsFile, byte[] bytes) {
    public String handle(MultipartFile mfile) {
        try {
//            String FILE_PATH = "C:/Users/박상우/Desktop/spring-a.2/out/artifacts/webapplication_Web_exploded/" + wsFile.getName();
            String FILE_PATH = "C:/Users/박상우/Desktop/spring-a.2/out/artifacts/webapplication_Web_exploded/test.png";
            File file = new File(FILE_PATH);
            FileCopyUtils.copy(mfile.getBytes(), file);

            System.out.println("file = " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"message\":\"testing\"}";
    }



    @PostMapping(value = "/wsfile")
    public ResponseEntity<String> ajax(MultipartFile mfile) {
        Message message = new Message();

        MFile mFile = fileUploadUtility.uploadFile(mfile, null);
        message.put("file", mFile);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.TEST_SUCCESS, message.getHashMap()), HttpStatus.OK);
    }
}

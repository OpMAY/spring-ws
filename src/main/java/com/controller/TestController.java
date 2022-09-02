package com.controller;

import com.aws.file.FileUploadUtility;
import com.transfer.DownloadBuilder;
import com.util.Encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {
    @Value("${KAKAO.CLIENT_ID}")
    private String KAKAO_KEY;
    @Value("${KAKAO.CLIENT_SECRET}")
    private String KAKAO_SECRET;
    @Value("${KAKAO.JAVASCRIPT}")
    private String KAKAO_JAVASCRIPT;
    @Value("${NAVER.CLIENT_ID}")
    private String NAVER_KEY;
    @Value("${NAVER.CLIENT_SECRET}")
    private String NAVER_SECRET;
    @Value("${GOOGLE.CLIENT_ID}")
    private String GOOGLE_KEY;
    @Value("${GOOGLE.CLIENT_SECRET}")
    private String GOOGLE_SECRET;

    private final FileUploadUtility fileUploadUtility;

    public static void main(String[] args) {
        try {
            System.out.println(new EncryptionService().encryptAES("smtp.naver.com", false));
            System.out.println(new EncryptionService().encryptAES("587", false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/test/login", method = RequestMethod.GET)
    public ModelAndView loginTest() {
        ModelAndView VIEW = new ModelAndView("test");
        VIEW.addObject("KAKAO_KEY", KAKAO_KEY);
        VIEW.addObject("KAKAO_SECRET", KAKAO_SECRET);
        VIEW.addObject("KAKAO_JAVASCRIPT", KAKAO_JAVASCRIPT);
        VIEW.addObject("NAVER_KEY", NAVER_KEY);
        VIEW.addObject("NAVER_SECRET", NAVER_SECRET);
        VIEW.addObject("GOOGLE_KEY", GOOGLE_KEY);
        VIEW.addObject("GOOGLE_SECRET", GOOGLE_SECRET);
        return VIEW;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("sample");
    }

    /**
     * <a href="/test/download.do?file_name=test.mp4"
     * class="btn btn-primary _card-btn">Go somewhere</a>
     */
    @Value("${UPLOAD_PATH}")
    private String UPLOAD_PATH;

    @RequestMapping(value = "/test/download", method = RequestMethod.GET)
    public void downloadTest(HttpServletResponse response, String file_name) {
        /**
         * Prepare AWS Download
         * And Logic After
         * */
        File file = new File(UPLOAD_PATH + file_name);
        try {
            DownloadBuilder downloadBuilder = new DownloadBuilder().init(response, true).file(file).header();
            downloadBuilder.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recover Interceptor Active
     */
    @RequestMapping(value = "/test/recover", method = RequestMethod.GET)
    public ModelAndView testRecover() {
        return new ModelAndView("error/recover");
    }
    @RequestMapping(value = "/test/error", method = RequestMethod.GET)
    public ModelAndView testError() {
        return new ModelAndView("error/error");
    }
}

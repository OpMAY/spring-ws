package com.controller;

import com.api.LoginAPI;
import com.exception.GrantAccessDeniedException;
import com.exception.enums.BusinessExceptionType;
import com.model.TestModel;
import com.service.HomeService;
import com.service.OtherHomeService;
import com.util.Folder;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@Slf4j
public class HomeController {
    private ModelAndView VIEW;

    @Autowired
    private HomeService homeService;

    @Autowired
    private OtherHomeService otherHomeService;

    /**
     * Init View Setting
     */
    public void HomeController() {
        VIEW = new ModelAndView("home");
    }

    @RequestMapping(value = "/directory.do", method = RequestMethod.GET)
    public ModelAndView directoryTest() {
        log.info(Folder.getFileType(new File("C:/Users/zlzld/OneDrive/Desktop/projects/sustable/spring-master/out/artifacts/webapplication_Web_exploded/files/test.txt")));
        return VIEW;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeGet() {
        HomeController();
        log.info("log4j2 test");
        //homeService.sqlRollbackTest();
        //homeService.sqlRollbackTest("test");
        //log.info("jsonTypeHandleTest");
        //homeService.insertJsonTypeHandleTest();
        //homeService.jsonTypeHandleTest();
        //homeService.insertJsonArrayTypeHandleTest();
        //homeService.jsonArrayTypeHandleTest();
        //homeService.insertJsonArrayRecursiveTypeHandleTest();
        return VIEW;
    }

    /**
     * Post 하다가 에러가 나서 ErrorHandling에 태웠을 경우
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView homePost() {
        HomeController();
        boolean error = true;
        if (error)
            throw new GrantAccessDeniedException(BusinessExceptionType.GRANT_EXCEPTION);
        return VIEW;
    }

    /**
     * 파라미터 타입은 다르지만 로직은 분리 해야할 때
     * Type parameter same but different values at sample, sample1, sample2
     */
    @RequestMapping(value = "/test/other.do", params = "type=general", method = RequestMethod.GET)
    public ModelAndView sample() {
        HomeController();
        log.info("general");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/test/other.do", params = "type=report", method = RequestMethod.GET)
    public ModelAndView sample1() {
        HomeController();
        log.info("report");
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/test/other.do", params = "type=update", method = RequestMethod.GET)
    public ModelAndView sample2() {
        HomeController();
        log.info("update");
        return new ModelAndView("home");
    }

    /**
     * 기본값 설정해서 Parameter 받기
     * localhost:8080/default.do?
     * "test" 라는 parameter가 없으면 test에 StringTest를 넣는다.
     * default value setting
     */
    @RequestMapping(value = "/test/default.do", method = RequestMethod.GET)
    public ModelAndView sample3(@RequestParam(value = "test", defaultValue = "StringTest") String test) {
        HomeController();
        log.info(test);
        return new ModelAndView("home");
    }

    /**
     * Exception Method Test
     */
    @RequestMapping(value = "/test/exception.do", method = RequestMethod.GET)
    public ModelAndView exception() {
        HomeController();
        throw new GrantAccessDeniedException(BusinessExceptionType.GRANT_EXCEPTION);
        /*return new ModelAndView("home");*/
    }

    /**
     * Default Value가 있는 Model의 경우
     */
    @RequestMapping(value = "/test/defaultModel.do", method = RequestMethod.GET)
    public ModelAndView defaultValueModel() {
        HomeController();
        TestModel testModel = new TestModel();
        return new ModelAndView("home");
    }

    /**
     * DAO Prototype Test
     */
    @RequestMapping(value = "/test/prototype.do", method = RequestMethod.GET)
    public ModelAndView prototype() {
        HomeController();
        otherHomeService.sqlRollbackTest();
        return new ModelAndView("home");
    }

    /**
     * File Download Logic Test
     * path.properties
     */

    @Value("filepath")
    private String path;

    @RequestMapping("/download/file.do")
    public ModelAndView download(@RequestParam("name") String filename) {
        HomeController();
        log.info(filename);
        File file = new File(path + filename);
        log.info(file.getName());
        return new ModelAndView("download", "downloadFile", file);
    }

    /**
     * Lombok Annotation Logic
     *
     * @RequiredArgsConstructor -> @NonNull 변수들로 생성자 만듬
     * @NoArgsConstructor -> 파라미터로 아무것도 받지 않는 생성자 (이 경우 @Data 못사용)
     * @AllArgsConstructor -> 전체 파라미터로 받는 생성자 생성 (이 경우 @Data 못사용)
     * @NonNull Annotation을 진행하면 생성자 생성시 해당 변수를 필수로 하는 생성자 생성
     */

    @AllArgsConstructor
    @RequiredArgsConstructor
    @NoArgsConstructor
    @Data //@Getter, @Setter, @RequiredArgsConstructor, @ToString @EqualsAndHashCode
    public class LombokClass1 {
        private String id;
        @NonNull
        private String password;
        private int u_id;
    }

    @RequestMapping(value = "/lombok1.do", method = RequestMethod.GET)
    public ModelAndView lombok1() {
        HomeController();
        LombokClass1 lombokClass1 = new LombokClass1("password");
        return VIEW;
    }

    /**
     * ----------------------------------------------------------------------
     */
    @Data //@Getter, @Setter, @RequiredArgsConstructor, @ToString @EqualsAndHashCode
    public class LombokClass2 {
        private String id;
        @NonNull
        private String password;
        private int u_id;
    }

    @RequestMapping(value = "/lombok2.do", method = RequestMethod.GET)
    public ModelAndView lombok2() {
        HomeController();
        LombokClass2 lombokClass2 = new LombokClass2("password");
        LombokClass2 lombokClass2_1 = new LombokClass2("password");
        log.info("Lombok Boolean Test 1 : " + lombokClass2_1.equals(lombokClass2));
        /*기댓값 true*/

        lombokClass2.setId("id");
        log.info("Lombok Boolean Test 2 : " + lombokClass2_1.equals(lombokClass2));
        /*기댓값 false*/
        return VIEW;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @RequiredArgsConstructor
    /*@ToString(exclude = "password")*/ //LombokClass3.toString() 진행 시 password 빼고 로그가 찍힌다.
    @ToString(exclude = {"password", "u_id"}) //LombokClass3.toString() 진행 시 password와 u_id 빼고 로그가 찍힌다.
    public class LombokClass3 {
        private String id;
        @NonNull
        private String password;
        private int u_id;
    }

    @RequestMapping(value = "/lombok3.do", method = RequestMethod.GET)
    public ModelAndView lombok3() {
        HomeController();
        LombokClass3 lombokClass3 = new LombokClass3();
        LombokClass3 lombokClass3_1 = new LombokClass3("password");
        LombokClass3 lombokClass3_2 = new LombokClass3("id", "password", 0);
        log.info(lombokClass3_2.toString());
        /*기댓값 ID만 뽑힘*/
        return VIEW;
    }

    /**
     * ----------------------------------------------------------------------
     */

    /**
     * SNS Login Test
     */
    @Autowired
    private LoginAPI loginAPI;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ModelAndView snsLogin(HttpServletRequest req) {
        HomeController();
        loginAPI.apiLoginInit(req);
        return VIEW;
    }

    /**
     * General Filter 임시 차단 URL Test
     */
    @RequestMapping(value = "/block.do", method = RequestMethod.GET)
    public ModelAndView block() {
        HomeController();
        log.info("해당 문자가 찍어야하는데 안찍힘 Filter에서 블락되었기 때문에");
        return VIEW;
    }

    /**
     * 위에서 URL TEST에서 실패할 경우 아래 함수로 들어온다.
     */
    @RequestMapping(value = "/error.do", method = RequestMethod.GET)
    public ModelAndView error() {
        HomeController();
        VIEW.setViewName("error/recover");
        return VIEW;
    }
}
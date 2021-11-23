package com.service;

import com.dao.TestDao;
import com.model.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Log4j
@PropertySource("classpath:aws.properties")
public class HomeService {

    @Value("${AWSModel.accessKey}")
    private String accessKey;
    @Value("${AWSModel.secretKey}")
    private String secretKey;
    @Value("${AWSModel.bucketName}")
    private String bucketName;

    @Autowired
    private TestDao testDao;

    /**
     * 읽기 전용 모드 많은 다중 Select 시에만
     * 1000++
     */
    @Transactional(readOnly = true)
    public ArrayList<Test> sqlRollbackTest() {
        try {
            ArrayList<Test> tests = testDao.selectTest();
            return tests;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Test>();
        } finally {
            log.info("sqlRollbackTest");
        }
    }

    public void cdnService() {
        log.info(accessKey);
        log.info(secretKey);
        log.info(bucketName);
    }

    public void jsonTypeHandleTest() {
        testDao.jsonTypeHandleTest(2);
    }

    public void insertJsonTypeHandleTest() {
        UserTest userTest = new UserTest();
        userTest.setPackage_path("com.model.UserTest");
        userTest.setEmail("zlzldntlr@naver.com");
        userTest.setAccess_token("token");
        userTest.setGrant("normal");
        userTest.setId("zlzldntlr");
        userTest.setName("김우식");
        UserContainer userContainer = new UserContainer();
        userContainer.setUsertest(userTest);
        testDao.insertJsonTypeHandleTest(userContainer);
    }

    public void insertJsonArrayTypeHandleTest() {
        ArrayTest arrayTest = new ArrayTest();
        ArrayList<UserTest> userTests = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserTest userTest = new UserTest();
            userTest.setPackage_path("com.model.UserTest");
            userTest.setEmail("zlzldntlr@naver.com");
            userTest.setAccess_token("token");
            userTest.setGrant("normal");
            userTest.setId("zlzldntlr");
            userTest.setName("김우식");
            userTests.add(userTest);
        }
        arrayTest.setUserTests(userTests);
        testDao.insertJsonArrayTypeHandleTest(arrayTest);
    }

    public void jsonArrayTypeHandleTest() {
        testDao.jsonArrayTypeHandleTest(1);
    }
}

package com.service;

import com.dao.TestDao;
import com.model.ArrayTest;
import com.model.Test;
import com.model.UserContainer;
import com.model.UserTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Slf4j
public class HomeService {

    @Value("${AWS.ACCESS}")
    private String accessKey;
    @Value("${AWS.SECRET}")
    private String secretKey;
    @Value("${AWS.BUCKET}")
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

    @Transactional
    public void sqlRollbackTest(String str) {
        Test test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());

        test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());
        if (str.equals("ex")) throw new RuntimeException("Exception");

        test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());
        /**If an error occurs in that part, the test succeeds.*/
        testDao.insertTestByNo(test);
    }

    @Transactional
    public void recursiveSqlRollbackTest(String str) {
        Test test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());

        innerLogic(str);

        test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());
        if (str.equals("ex")) throw new RuntimeException("Exception");

        test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());
    }

    @Transactional
    public void innerLogic(String str) {
        Test test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());

        if (str.equals("exinner")) throw new RuntimeException("Exception");

        test = new Test();
        test.setTestcol("string");
        testDao.insertTest(test);
        System.out.println("success : " + test.getNo());
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
        try {
            UserTest userTest = new UserTest();
            userTest.setEmail("zlzldntlr@naver.com");
            userTest.setAccess_token("token");
            userTest.setGrant("normal");
            userTest.setId("zlzldntlr");
            userTest.setName("김우식");
            UserContainer userContainer = new UserContainer();
            userContainer.setUsertest(userTest);
            testDao.insertJsonTypeHandleTest(userContainer);
        } catch (Exception e) {
        }
    }

    public void insertJsonArrayTypeHandleTest() {
        try {
            ArrayTest arrayTest = new ArrayTest();
            ArrayList<UserTest> userTests = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                UserTest userTest = new UserTest();
                userTest.setEmail("zlzldntlr@naver.com");
                userTest.setAccess_token("token");
                userTest.setGrant("normal");
                userTest.setId("zlzldntlr");
                userTest.setName("김우식");
                userTests.add(userTest);
            }
            arrayTest.setUserTests(userTests);
            testDao.insertJsonArrayTypeHandleTest(arrayTest);
        } catch (Exception e) {
        }
    }

    public void jsonArrayTypeHandleTest() {
        testDao.jsonArrayTypeHandleTest(1);
    }
}

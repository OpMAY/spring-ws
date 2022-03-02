package com.dao;

import com.mapper.TestMapper;
import com.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TestDao {

    private final SqlSession sqlSession;

    public ArrayList<Test> selectTest() throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectTest();
    }

    public void jsonTypeHandleTest(int no) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        log.info(testMapper.jsonTypeHandleTest(no).getUsertest().getEmail());
    }

    public void insertJsonTypeHandleTest(UserContainer userContainer) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertJsonTypeHandleTest(userContainer);
    }

    public void insertJsonArrayTypeHandleTest(ArrayTest arrayTest) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertJsonArrayTypeHandleTest(arrayTest);
    }

    public void jsonArrayTypeHandleTest(int no) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        log.info(testMapper.jsonArrayTypeHandleTest(no).getUserTests().get(0).getEmail());
    }

    public void insertTest(Test test) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertTest(test);
    }

    public void insertTestByNo(Test test) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertTestByNo(test);
    }

    public List<User> selectUsers() {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectUsers();
    }

    public void insertUser(User user) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertUser(user);
    }

    public void insertFileBulk(SplitFileData split) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertFileBulk(split);
    }
}
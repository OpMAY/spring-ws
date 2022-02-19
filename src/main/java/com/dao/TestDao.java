package com.dao;

import com.mapper.TestMapper;
import com.model.*;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@Repository
@Log4j
public class TestDao {
    private SqlSession sqlSession;

    public void setSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

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
}
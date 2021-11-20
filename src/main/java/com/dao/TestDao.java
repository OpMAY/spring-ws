package com.dao;

import com.mapper.TestMapper;
import com.model.Test;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@Repository
@Scope("prototype")
@Log4j
public class TestDao {
    private static int i = 0;

    public TestDao() {
        log.info("Prototype Test : " + i);
    }

    @Autowired
    private SqlSession sqlSession;

    public ArrayList<Test> selectTest() throws SQLException {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectTest();
    }
}
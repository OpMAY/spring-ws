package com.dao;

import com.mapper.TestMapper;
import com.model.*;
import com.model.mybatis.ArrayTestModel;
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

    public SplitFileData selectFileByNameAndIndex(String name, Integer start_index, Integer last_index) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectFileByNameAndIndex(name, start_index, last_index);
    }

    public Integer selectDataCountByFileName(String file_name) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectDataCountByFileName(file_name);
    }

    public SplitFileData selectFileByNo(int split_file_data_no) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectFileByNo(split_file_data_no);
    }

    public SplitFileData selectInsertQueue() {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectInsertQueue();
    }

    public ArrayList<SplitFileData> selectFileByName(String file_name) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectFileByName(file_name);
    }

    public void insertEndFileBulk(SplitFileData split) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.insertEndFileBulk(split);
    }

    public void updateEndFileBulk(SplitFileData split) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.updateEndFileBulk(split);
    }

    public void updateCompleteFileBulk(SplitFileData splitFileData) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.updateCompleteFileBulk(splitFileData);
    }

    public ArrayList<SplitFileData> selectFileAllByName(String file_name) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.selectFileAllByName(file_name);
    }

    public void arrayTestModelInsertTest(ArrayTestModel arrayTestModel) {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        testMapper.arrayTestModelInsertTest(arrayTestModel);
    }

    public ArrayList<ArrayTestModel> arrayTestModelSelectTest() {
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        return testMapper.arrayTestModelSelectTest();
    }
}
package com.service;

import com.dao.TestDao;
import com.model.mybatis.ArrayTestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class MybatisService {

    @Autowired
    private TestDao testDao;


    public void arrayTestModelInsertTest(ArrayTestModel arrayTestModel) {
        testDao.arrayTestModelInsertTest(arrayTestModel);
    }

    public ArrayList<ArrayTestModel> arrayTestModelSelectTest() {
        return testDao.arrayTestModelSelectTest();
    }
}

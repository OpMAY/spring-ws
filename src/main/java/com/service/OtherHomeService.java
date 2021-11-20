package com.service;

import com.dao.TestDao;
import com.model.Test;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
@Log4j
public class OtherHomeService {

    @Autowired
    private TestDao testDao;

    /**읽기 전용 모드 많은 다중 Select 시에만
     * 1000++*/
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
}

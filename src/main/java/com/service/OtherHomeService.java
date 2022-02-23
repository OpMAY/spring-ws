package com.service;

import com.dao.TestDao;
import com.model.Test;
import com.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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

    public List<User> selectUsers() {
        List<User> users = testDao.selectUsers();
        return users;
    }

    public void insertUser(User user) {
        testDao.insertUser(user);
    }
}

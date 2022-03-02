package com.service;

import com.dao.TestDao;
import com.model.ArrayTest;
import com.model.SplitFileData;
import com.model.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BulkFileService {

    @Autowired
    private TestDao testDao;

    public void insertFileBulk(SplitFileData split) {
        try {
            testDao.insertFileBulk(split);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

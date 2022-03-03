package com.service;

import com.dao.TestDao;
import com.model.SplitFileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SplitFileData selectFileByNameAndIndex(String name, Integer start_index, Integer last_index) {
        return testDao.selectFileByNameAndIndex(name, start_index, last_index);
    }

    public Integer selectDataCountByFileName(String file_name) {
        return testDao.selectDataCountByFileName(file_name);
    }

    public SplitFileData selectFileByNo(int split_file_data_no) {
        return testDao.selectFileByNo(split_file_data_no);
    }
}

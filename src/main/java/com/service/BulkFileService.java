package com.service;

import com.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulkFileService {

    @Autowired
    private TestDao testDao;

}

package com.mapper;

import com.model.Test;
import com.model.UserContainer;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TestMapper {
    ArrayList<Test> selectTest();

    UserContainer jsonTypeHandleTest(@Param("no") int no);
}

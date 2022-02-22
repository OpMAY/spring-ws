package com.mapper;

import com.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TestMapper {
    ArrayList<Test> selectTest();

    UserContainer jsonTypeHandleTest(@Param("no") int no);

    void insertJsonTypeHandleTest(UserContainer userTest);

    void insertJsonArrayTypeHandleTest(ArrayTest arrayTest);

    ArrayTest jsonArrayTypeHandleTest(@Param("no") int no);

    void insertTest(Test test);

    void insertTestByNo(Test test);
}

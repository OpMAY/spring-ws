package com.mapper;

import com.controller.TestController;
import com.model.*;
import com.model.mybatis.ArrayTestModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface TestMapper {
    ArrayList<Test> selectTest();

    UserContainer jsonTypeHandleTest(@Param("no") int no);

    void insertJsonTypeHandleTest(UserContainer userTest);

    void insertJsonArrayTypeHandleTest(ArrayTest arrayTest);

    ArrayTest jsonArrayTypeHandleTest(@Param("no") int no);

    void insertTest(Test test);

    void insertTestByNo(Test test);

    List<User> selectUsers();

    void insertUser(User user);

    void insertFileBulk(SplitFileData split);

    SplitFileData selectFileByNameAndIndex(@Param("name") String name, @Param("start_index") Integer start_index, @Param("last_index") Integer last_index);

    Integer selectDataCountByFileName(String file_name);

    SplitFileData selectFileByNo(@Param("no") int split_file_data_no);

    SplitFileData selectInsertQueue();

    ArrayList<SplitFileData> selectFileByName(@Param("file_name") String file_name);

    void insertEndFileBulk(SplitFileData split);

    void updateEndFileBulk(SplitFileData split);

    void updateCompleteFileBulk(SplitFileData splitFileData);

    ArrayList<SplitFileData> selectFileAllByName(@Param("file_name") String file_name);

    void arrayTestModelInsertTest(ArrayTestModel arrayTestModel);

    ArrayList<ArrayTestModel> arrayTestModelSelectTest();
}

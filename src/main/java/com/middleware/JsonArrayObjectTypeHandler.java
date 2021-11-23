package com.middleware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.json.JSONArray;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Log4j
public class JsonArrayObjectTypeHandler<T> extends BaseTypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, new Gson().toJson(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        log.info("getNullableResult +s: " + s + resultSet.getString(s));
        return convertToObject(resultSet.getString(s));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        log.info("getNullableResult +i: " + i + resultSet.getString(i));
        return convertToObject(resultSet.getString(i));
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        log.info("getNullableResult +CallableStatement: " + i + callableStatement.getString(i));
        return convertToObject(callableStatement.getString(i));
    }

    private T convertToObject(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            Class<?> findClass = null;
            if (jsonArray.length() != 0) {
                findClass = Class.forName(jsonArray.getJSONObject(0).getString("package_path"));
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add((T) new ObjectMapper().readValue(jsonArray.getJSONObject(i).toString(), findClass));
                }
                return (T) arrayList;
            } else {
                return null;
            }
        } catch (ClassNotFoundException e) {
            log.error("JSONTypeHandler failed to convert jsonString to list, JSON String : " + jsonString, e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

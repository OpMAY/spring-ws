package com.middleware;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

@Log4j
public class JsonObjectTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
            throws SQLException {
        String p = JacksonParsing.toString(parameter);
        ps.setObject(i, p);
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Object d = rs.getObject(columnName);
        if(d == null) return d;
        return JacksonParsing.toObject(d.toString());
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Object d = rs.getObject(columnIndex);
        if(d == null) return d;
        return JacksonParsing.toObject(d.toString());
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Object d = cs.getObject(columnIndex);
        if(d == null) return d;
        return JacksonParsing.toObject(d.toString());
    }
}

package com.rnb.demo.persistence.handler;

import com.rnb.demo.entity.constants.ParameterType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ParameterType.class)
@MappedJdbcTypes(JdbcType.CHAR)
public class ParameterTypeEnumTypeHandler implements TypeHandler<ParameterType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, ParameterType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public ParameterType getResult(ResultSet rs, String columnName) throws SQLException {
        String key = rs.getString(columnName);
        return ParameterType.getByKey(key);
    }

    @Override
    public ParameterType getResult(ResultSet rs, int columnIndex) throws SQLException {
        String key = rs.getString(columnIndex);
        return ParameterType.getByKey(key);
    }

    @Override
    public ParameterType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String key = cs.getString(columnIndex);
        return ParameterType.getByKey(key);
    }
}

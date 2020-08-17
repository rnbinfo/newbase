package com.rnb.demo.persistence.handler;

import com.rnb.demo.entity.constants.DataDictionaryType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(DataDictionaryType.class)
@MappedJdbcTypes(JdbcType.CHAR)
public class DataDictionaryTypeEnumTypeHandler implements TypeHandler<DataDictionaryType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, DataDictionaryType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public DataDictionaryType getResult(ResultSet rs, String columnName) throws SQLException {
        String key = rs.getString(columnName);
        return DataDictionaryType.getByKey(key);
    }

    @Override
    public DataDictionaryType getResult(ResultSet rs, int columnIndex) throws SQLException {
        String key = rs.getString(columnIndex);
        return DataDictionaryType.getByKey(key);
    }

    @Override
    public DataDictionaryType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String key = cs.getString(columnIndex);
        return DataDictionaryType.getByKey(key);
    }
}

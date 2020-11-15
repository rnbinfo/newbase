package com.rnb.newbase.persistence.handler;

import com.rnb.newbase.entity.CommonStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(CommonStatus.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CommonStatusEnumTypeHandler implements TypeHandler<CommonStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, CommonStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public CommonStatus getResult(ResultSet rs, String columnName) throws SQLException {
        String key = rs.getString(columnName);
        return CommonStatus.getByKey(key);
    }

    @Override
    public CommonStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        String key = rs.getString(columnIndex);
        return CommonStatus.getByKey(key);
    }

    @Override
    public CommonStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String key = cs.getString(columnIndex);
        return CommonStatus.getByKey(key);
    }
}

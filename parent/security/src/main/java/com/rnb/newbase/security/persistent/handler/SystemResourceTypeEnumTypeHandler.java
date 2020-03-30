package com.rnb.newbase.security.persistent.handler;

import com.rnb.newbase.security.persistent.entity.constant.SystemResourceType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SystemResourceType.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SystemResourceTypeEnumTypeHandler implements TypeHandler<SystemResourceType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SystemResourceType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public SystemResourceType getResult(ResultSet rs, String columnName) throws SQLException {
        String key = rs.getString(columnName);
        return SystemResourceType.getByKey(key);
    }

    @Override
    public SystemResourceType getResult(ResultSet rs, int columnIndex) throws SQLException {
        String key = rs.getString(columnIndex);
        return SystemResourceType.getByKey(key);
    }

    @Override
    public SystemResourceType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String key = cs.getString(columnIndex);
        return SystemResourceType.getByKey(key);
    }
}

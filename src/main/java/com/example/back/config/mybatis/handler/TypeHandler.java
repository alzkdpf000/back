package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Provider;
import com.example.back.common.enumeration.Type;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Type.class)
public class TypeHandler implements org.apache.ibatis.type.TypeHandler<Type> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Type parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Type getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "spend" -> Type.SPEND;
            case "charge" -> Type.CHARGE;
            default -> null;
        };
    }

    @Override
    public Type getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "spend" -> Type.SPEND;
            case "charge" -> Type.CHARGE;
            default -> null;
        };
    }

    @Override
    public Type getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "spend" -> Type.SPEND;
            case "charge" -> Type.CHARGE;
            default -> null;
        };
    }
}

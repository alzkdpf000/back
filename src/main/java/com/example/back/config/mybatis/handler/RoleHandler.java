package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Acceptance;
import com.example.back.common.enumeration.Role;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Role.class)
public class RoleHandler implements TypeHandler<Role> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Role getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "member" -> Role.MEMBER;
            case "admin" -> Role.ADMIN;
            case "doctor" -> Role.DOCTOR;
            default -> null;
        };
    }

    @Override
    public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "member" -> Role.MEMBER;
            case "admin" -> Role.ADMIN;
            case "doctor" -> Role.DOCTOR;
            default -> null;
        };
    }

    @Override
    public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "member" -> Role.MEMBER;
            case "admin" -> Role.ADMIN;
            case "doctor" -> Role.DOCTOR;
            default -> null;
        };
    }
}

package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Provider;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Provider.class)
public class ProviderHandler implements TypeHandler<Provider> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Provider parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Provider getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "kakao" -> Provider.KAKAO;
            case "goldentime" -> Provider.GOLDENTIME;
            default -> null;
        };
    }

    @Override
    public Provider getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "kakao" -> Provider.KAKAO;
            case "goldentime" -> Provider.GOLDENTIME;
            default -> null;
        };
    }

    @Override
    public Provider getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "kakao" -> Provider.KAKAO;
            case "goldentime" -> Provider.GOLDENTIME;
            default -> null;
        };
    }
}

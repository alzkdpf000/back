package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Accpetance;
import com.example.back.common.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Accpetance.class)
public class AccpetanceHandler implements TypeHandler<Accpetance> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Accpetance parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Accpetance getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "accepted" -> Accpetance.ACCEPTED;
            case "unaccepted" -> Accpetance.UNACCEPTED;
            default -> null;
        };
    }

    @Override
    public Accpetance getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "accepted" -> Accpetance.ACCEPTED;
            case "unaccepted" -> Accpetance.UNACCEPTED;
            default -> null;
        };
    }

    @Override
    public Accpetance getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "accepted" -> Accpetance.ACCEPTED;
            case "unaccepted" -> Accpetance.UNACCEPTED;
            default -> null;
        };
    }
}

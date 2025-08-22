package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Acceptance;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Acceptance.class)
public class AcceptanceHandler implements TypeHandler<Acceptance> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Acceptance parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Acceptance getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "accepted" -> Acceptance.ACCEPTED;
            case "unaccepted" -> Acceptance.UNACCEPTED;
            default -> null;
        };
    }

    @Override
    public Acceptance getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "accepted" -> Acceptance.ACCEPTED;
            case "unaccepted" -> Acceptance.UNACCEPTED;
            default -> null;
        };
    }

    @Override
    public Acceptance getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "accepted" -> Acceptance.ACCEPTED;
            case "unaccepted" -> Acceptance.UNACCEPTED;
            default -> null;
        };
    }
}

package com.example.back.config.mybatis.handler;

import com.example.back.common.enumeration.Accpetance;
import com.example.back.common.enumeration.Result;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Result.class)
public class ResultHandler implements TypeHandler<Result> {


    @Override
    public void setParameter(PreparedStatement ps, int i, Result parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Result getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)) {
            case "done" -> Result.DONE;
            case "cancel" -> Result.CANCEL;
            default -> null;
        };
    }

    @Override
    public Result getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)) {
            case "done" -> Result.DONE;
            case "cancel" -> Result.CANCEL;
            default -> null;
        };
    }

    @Override
    public Result getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)) {
            case "done" -> Result.DONE;
            case "cancel" -> Result.CANCEL;
            default -> null;
        };
    }
}

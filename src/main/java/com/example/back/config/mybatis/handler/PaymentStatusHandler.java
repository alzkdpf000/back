package com.example.back.config.mybatis.handler;
import com.example.back.common.enumeration.PaymentStatus;
import com.example.back.common.enumeration.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PaymentStatus.class)
public class PaymentStatusHandler implements TypeHandler<PaymentStatus> {
    @Override
    public void setParameter(PreparedStatement ps, int i, PaymentStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public PaymentStatus getResult(ResultSet rs, String columnName) throws SQLException {
        return switch (rs.getString(columnName)){
            case "pending" -> PaymentStatus.PENDING;
            case "success" -> PaymentStatus.SUCCESS;
            case "cancel" -> PaymentStatus.CANCEL;
            default -> null;
        };
    }

    @Override
    public PaymentStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        return switch (rs.getString(columnIndex)){
            case "pending" -> PaymentStatus.PENDING;
            case "success" -> PaymentStatus.SUCCESS;
            case "cancel" -> PaymentStatus.CANCEL;
            default -> null;
        };
    }

    @Override
    public PaymentStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return switch (cs.getString(columnIndex)){
            case "pending" -> PaymentStatus.PENDING;
            case "success" -> PaymentStatus.SUCCESS;
            case "cancel" -> PaymentStatus.CANCEL;
            default -> null;
        };
    }
}

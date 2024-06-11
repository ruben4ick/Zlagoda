package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Check;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckRowMapper implements RowMapper<Check> {

    @Override
    public Check mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Check.builder()
                .checkNumber(rs.getString("check_number"))
                .idEmployee(rs.getString("id_employee"))
                .cardNumber(rs.getString("card_number"))
                .printDate(rs.getTimestamp("print_date"))
                .totalSum(rs.getBigDecimal("sum_total"))
                .vat(rs.getBigDecimal("vat"))
                .build();
    }
}

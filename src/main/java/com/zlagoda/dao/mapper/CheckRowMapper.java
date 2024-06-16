package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Check;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckRowMapper implements RowMapper<Check> {

    @Override
    public Check mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = Employee.builder()
                .id(rs.getString("id_employee"))
                .surname(rs.getString("empl_surname"))
                .name(rs.getString("empl_name"))
                .build();

        CustomerCard customerCard = null;
        if (rs.getString("card_number") != null) {
            customerCard = CustomerCard.builder()
                    .cardNumber(rs.getString("card_number"))
                    .surname(rs.getString("cust_surname"))
                    .name(rs.getString("cust_name"))
                    .build();
        }

        return Check.builder()
                .checkNumber(rs.getString("check_number"))
                .employee(employee)
                .customerCard(customerCard)
                .printDate(rs.getTimestamp("print_date").toLocalDateTime())
                .totalSum(rs.getBigDecimal("sum_total"))
                .vat(rs.getBigDecimal("vat"))
                .build();
    }
}

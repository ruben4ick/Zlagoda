package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Check;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CheckRowMapper implements RowMapper<Check> {

    @Override
    public Check mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = Employee.builder()
                .id(rs.getString("id_employee"))
                .surname(rs.getString("empl_surname"))
                .name(rs.getString("empl_name"))
                .patronymic(rs.getString("empl_patronymic"))
                .role(Employee.Role.valueOf(rs.getString("empl_role")))
                .salary(rs.getBigDecimal("salary"))
                .birthDate(rs.getDate("date_of_birth"))
                .startDate(rs.getDate("date_of_start"))
                .phoneNumber(rs.getString("phone_number"))
                .city(rs.getString("city"))
                .street(rs.getString("street"))
                .zipCode(rs.getString("zip_code"))
                .build();

        CustomerCard customerCard = rs.getString("card_number") != null ? CustomerCard.builder()
                .cardNumber(rs.getString("card_number"))
                .surname(rs.getString("cust_surname"))
                .name(rs.getString("cust_name"))
                .patronymic(rs.getString("cust_patronymic"))
                .phoneNumber(rs.getString("phone_number"))
                .city(rs.getString("city"))
                .street(rs.getString("street"))
                .zipCode(rs.getString("zip_code"))
                .percent(rs.getInt("percent"))
                .build() : null;

        return Check.builder()
                .checkNumber(rs.getString("check_number"))
                .employee(employee)
                .customerCard(customerCard)
                .printDate(new Date(rs.getTimestamp("print_date").getTime()))
                .totalSum(rs.getBigDecimal("total_sum"))
                .vat(rs.getBigDecimal("vat"))
                .build();
    }
}

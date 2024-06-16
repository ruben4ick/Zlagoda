package com.zlagoda.dao.mapper;

import com.zlagoda.entity.CustomerCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCardRowMapper implements RowMapper<CustomerCard> {

    @Override
    public CustomerCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerCard.builder()
                .cardNumber(rs.getString("card_number"))
                .surname(rs.getString("cust_surname"))
                .name(rs.getString("cust_name"))
                .patronymic(rs.getString("cust_patronymic"))
                .phoneNumber(rs.getString("phone_number"))
                .city(rs.getString("city"))
                .street(rs.getString("street"))
                .zipCode(rs.getString("zip_code"))
                .percent(rs.getInt("percent"))
                .build();
    }
}

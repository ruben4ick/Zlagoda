package com.zlagoda.dao.impl;

import com.zlagoda.dao.CustomerCardDao;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.dao.mapper.CustomerCardRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerCardDaoImpl implements CustomerCardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL = "SELECT * FROM Customer_Card ORDER BY cust_surname";
    private static final String FIND_BY_ID = "SELECT * FROM Customer_Card WHERE card_number = ?";
    private static final String INSERT = "INSERT INTO Customer_Card (card_number, cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Customer_Card SET cust_surname = ?, cust_name = ?, cust_patronymic = ?, phone_number = ?, city = ?, street = ?, zip_code = ?, percent = ? WHERE card_number = ?";
    private static final String DELETE = "DELETE FROM Customer_Card WHERE card_number = ?";

    private static final String FIND_BY_PERCENT = "SELECT * FROM Customer_Card WHERE percent = ? ORDER BY surname";

    @Override
    public List<CustomerCard> getAll() {
        return jdbcTemplate.query(FIND_ALL, new CustomerCardRowMapper());
    }

    @Override
    public Optional<CustomerCard> getById(String cardNumber) {
        List<CustomerCard> customers = jdbcTemplate.query(FIND_BY_ID, new Object[]{cardNumber}, new CustomerCardRowMapper());
        return customers.stream().findFirst();
    }

    @Override
    public void create(CustomerCard customerCard) {
        String generatedNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 13);
        customerCard.setCardNumber(generatedNumber);
        Object[] params = {
                customerCard.getCardNumber(),
                customerCard.getSurname(),
                customerCard.getName(),
                customerCard.getPatronymic(),
                customerCard.getPhoneNumber(),
                customerCard.getCity(),
                customerCard.getStreet(),
                customerCard.getZipCode(),
                customerCard.getPercent()
        };
        jdbcTemplate.update(INSERT, params);
    }

    @Override
    public void update(CustomerCard customerCard) {
        Object[] params = {
                customerCard.getSurname(),
                customerCard.getName(),
                customerCard.getPatronymic(),
                customerCard.getPhoneNumber(),
                customerCard.getCity(),
                customerCard.getStreet(),
                customerCard.getZipCode(),
                customerCard.getPercent(),
                customerCard.getCardNumber()
        };
        jdbcTemplate.update(UPDATE, params);
    }

    @Override
    public void delete(String cardNumber) {
        jdbcTemplate.update(DELETE, cardNumber);
    }

    @Override
    public List<CustomerCard> findByPercent(int percent) {
        return jdbcTemplate.query(FIND_BY_PERCENT, new Object[]{percent}, new CustomerCardRowMapper());
    }
}

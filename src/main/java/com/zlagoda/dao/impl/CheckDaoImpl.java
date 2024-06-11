package com.zlagoda.dao.impl;

import com.zlagoda.dao.CheckDao;
import com.zlagoda.dao.mapper.CheckRowMapper;
import com.zlagoda.entity.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CheckDaoImpl implements CheckDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CHECKS =
            "SELECT c.*, e.id_employee, e.empl_name, e.empl_surname, cc.card_number, cc.cust_name, cc.cust_surname " +
                    "FROM `Check` c " +
                    "JOIN Employee e ON c.id_employee = e.id_employee " +
                    "LEFT JOIN Customer_Card cc ON c.card_number = cc.card_number " +
                    "ORDER BY c.print_date DESC";

    private static final String FIND_BY_ID =
            "SELECT c.*, e.id_employee, e.empl_name, e.empl_surname, cc.card_number, cc.cust_name, cc.cust_surname " +
                    "FROM `Check` c " +
                    "JOIN Employee e ON c.id_employee = e.id_employee " +
                    "LEFT JOIN Customer_Card cc ON c.card_number = cc.card_number " +
                    "WHERE c.check_number = ?";

    private static final String INSERT_CHECK =
            "INSERT INTO `Check` (check_number, id_employee, card_number, print_date, sum_total, vat) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_CHECK =
            "UPDATE `Check` SET id_employee = ?, card_number = ?, print_date = ?, sum_total = ?, vat = ? WHERE check_number = ?";

    private static final String DELETE_CHECK =
            "DELETE FROM `Check` WHERE check_number = ?";

    @Override
    public List<Check> getAll() {
        return jdbcTemplate.query(FIND_ALL_CHECKS, new CheckRowMapper());
    }

    @Override
    public Optional<Check> getById(String checkNumber) {
        List<Check> checks = jdbcTemplate.query(FIND_BY_ID, new Object[]{checkNumber}, new CheckRowMapper());
        return checks.stream().findFirst();
    }

    @Override
    public void create(Check check) {
        jdbcTemplate.update(INSERT_CHECK,
                check.getCheckNumber(),
                check.getEmployee().getId(),
                check.getCustomerCard() != null ? check.getCustomerCard().getCardNumber() : null,
                check.getPrintDate(),
                check.getTotalSum(),
                check.getVat());
    }

    @Override
    public void update(Check check) {
        //not needed
    }

    @Override
    public void delete(String checkNumber) {
        jdbcTemplate.update(DELETE_CHECK, checkNumber);
    }
}

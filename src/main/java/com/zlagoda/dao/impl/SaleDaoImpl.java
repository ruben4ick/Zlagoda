package com.zlagoda.dao.impl;


import com.zlagoda.dao.SaleDao;
import com.zlagoda.dao.mapper.SaleRowMapper;
import com.zlagoda.entity.Sale;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaleDaoImpl implements SaleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SALES = "SELECT * FROM Sale";
    private static final String FIND_BY_UPC_AND_CHECK_NUMBER = "SELECT * FROM Sale WHERE UPC = ? AND check_number = ?";
    private static final String INSERT_SALE = "INSERT INTO Sale (UPC, check_number, product_number, selling_price) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SALE = "UPDATE Sale SET product_number = ?, selling_price = ? WHERE UPC = ? AND check_number = ?";
    private static final String DELETE_SALE = "DELETE FROM Sale WHERE UPC = ? AND check_number = ?";

    @Override
    public List<Sale> getAll() {
        return jdbcTemplate.query(FIND_ALL_SALES, new SaleRowMapper());
    }

    @Override
    public Optional<Sale> getById(Pair<String, String> upcAndCheckNumber) {
        List<Sale> sales = jdbcTemplate.query(FIND_BY_UPC_AND_CHECK_NUMBER, new SaleRowMapper(), upcAndCheckNumber.getLeft(), upcAndCheckNumber.getRight());
        return sales.stream().findFirst();
    }

    @Override
    public void create(Sale sale) {
        jdbcTemplate.update(INSERT_SALE, sale.getStoreProduct().getUpc(), sale.getCheckNumber(), sale.getProductNumber(), sale.getSellingPrice());
    }

    @Override
    public void update(Sale sale) {
        jdbcTemplate.update(UPDATE_SALE, sale.getProductNumber(), sale.getSellingPrice(), sale.getStoreProduct().getUpc(), sale.getCheckNumber());
    }

    @Override
    public void delete(Pair<String, String> upcAndCheckNumber) {
        jdbcTemplate.update(DELETE_SALE, upcAndCheckNumber.getLeft(), upcAndCheckNumber.getRight());
    }
}

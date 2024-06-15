package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Sale;
import com.zlagoda.entity.StoreProduct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SaleRowMapper implements RowMapper<Sale> {
    @Override
    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sale(
                StoreProduct.builder().upc(rs.getString("UPC")).build(),
                rs.getString("check_number"),
                rs.getInt("product_number"),
                rs.getBigDecimal("selling_price")
        );
    }
}

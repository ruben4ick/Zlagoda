package com.zlagoda.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zlagoda.entity.ProductClassic;
import org.springframework.jdbc.core.RowMapper;

public class ProductClassicRowMapper implements RowMapper<ProductClassic> {

    @Override
    public ProductClassic mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductClassic.builder()
                .categoryName(rs.getString("category_name"))
                .productName(rs.getString("product_name"))
                .productCount(rs.getInt("product_count"))
                .build();
    }
}

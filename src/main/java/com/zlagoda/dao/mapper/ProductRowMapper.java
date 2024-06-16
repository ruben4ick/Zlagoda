package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Category;
import com.zlagoda.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = Category.builder()
                .number(rs.getLong("category_number"))
                .name(rs.getString("category_name"))
                .build();

        return Product.builder()
                .id(rs.getLong("id_product"))
                .category(category) // Замість categoryNumber
                .name(rs.getString("product_name"))
                .characteristics(rs.getString("characteristics"))
                .build();
    }
}
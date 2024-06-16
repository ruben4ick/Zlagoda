package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    private static final String NUMBER = "category_number";
    private static final String NAME = "category_name";


    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .number(rs.getLong(NUMBER))
                .name(rs.getString(NAME))
                .build();
    }
}

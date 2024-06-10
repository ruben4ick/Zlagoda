package com.zlagoda.dao.impl;

import com.zlagoda.dao.CategoryDao;
import com.zlagoda.dao.mapper.CategoryRowMapper;
import com.zlagoda.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CATEGORIES = "SELECT * FROM Category ORDER BY category_name";
    private static final String FIND_BY_ID = "SELECT * FROM Category WHERE category_number = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO Category (category_number, category_name) VALUES (?, ?)";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET category_name = ? WHERE category_number = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM Category WHERE category_number = ?";

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(FIND_ALL_CATEGORIES, new CategoryRowMapper());
    }

    @Override
    public Optional<Category> getById(Long categoryNumber) {
        List<Category> categories = jdbcTemplate.query(FIND_BY_ID, new Object[]{categoryNumber}, new CategoryRowMapper());
        return categories.stream().findFirst();
    }

    @Override
    public void create(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY, category.getNumber(), category.getName());
    }

    @Override
    public void update(Category category) {
        jdbcTemplate.update(UPDATE_CATEGORY, category.getName(), category.getNumber());
    }

    @Override
    public void delete(Long categoryNumber) {
        jdbcTemplate.update(DELETE_CATEGORY, categoryNumber);
    }
}
package com.zlagoda.dao.impl;

import com.zlagoda.dao.ProductDao;
import com.zlagoda.dao.mapper.ProductRowMapper;
import com.zlagoda.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_PRODUCTS = "SELECT * " +
            "FROM Product p " +
            "INNER JOIN Category c ON p.category_number = c.category_number " +
            "ORDER BY p.product_name";

    private static final String FIND_BY_ID = "SELECT * " +
            "FROM Product p " +
            "INNER JOIN Category c ON p.category_number = c.category_number " +
            "WHERE p.id_product = ?";

    private static final String INSERT_PRODUCT = "INSERT INTO Product (category_number, product_name, characteristics) VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET category_number = ?, product_name = ?, characteristics = ? WHERE id_product = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE id_product = ?";
    private static final String FIND_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM Product WHERE category_number = ? ORDER BY product_name";

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(FIND_ALL_PRODUCTS, new ProductRowMapper());
    }

    @Override
    public Optional<Product> getById(Long productId) {
        List<Product> products = jdbcTemplate.query(FIND_BY_ID, new Object[]{productId}, new ProductRowMapper());
        return products.stream().findFirst();
    }

    @Override
    public void create(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getCategory().getNumber(),
                product.getName(),
                product.getCharacteristics());
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT,
                product.getCategory().getNumber(),
                product.getName(),
                product.getCharacteristics(),
                product.getId());
    }

    @Override
    public void delete(Long productId) {
        jdbcTemplate.update(DELETE_PRODUCT, productId);
    }

    public List<Product> findByCategory(Long categoryId) {
        return jdbcTemplate.query(FIND_PRODUCTS_BY_CATEGORY, new Object[]{categoryId}, new ProductRowMapper());
    }
}
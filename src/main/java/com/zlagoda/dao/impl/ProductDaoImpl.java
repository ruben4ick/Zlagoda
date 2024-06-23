package com.zlagoda.dao.impl;

import com.zlagoda.dao.ProductDao;
import com.zlagoda.dao.mapper.ProductRowMapper;
import com.zlagoda.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    private static final String FIND_PRODUCTS_BY_NAME = "SELECT * " +
            "FROM Product p " +
            "INNER JOIN Category c ON p.category_number = c.category_number " +
            "WHERE product_name LIKE ?" +
            "ORDER BY p.product_name";

    private static final String FIND_BY_ID = "SELECT * " +
            "FROM Product p " +
            "INNER JOIN Category c ON p.category_number = c.category_number " +
            "WHERE p.id_product = ?";

    private static final String INSERT_PRODUCT = "INSERT INTO Product (category_number, product_name, characteristics) VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET category_number = ?, product_name = ?, characteristics = ? WHERE id_product = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE id_product = ?";
    private static final String FIND_PRODUCTS_BY_CATEGORY =
            "SELECT * " +
                    "FROM Product p " +
                    "INNER JOIN Category c ON p.category_number = c.category_number " +
                    "WHERE p.category_number = ? " +
                    "ORDER BY p.product_name;";
    private static final String FIND_SUM_OF_PRODUCT_SALES_BY_NAME_AND_DATES = "SELECT " +
            "SUM(s.product_number) AS total_sales " +
            "FROM sale s " +
            "INNER JOIN store_product sp ON s.UPC = sp.UPC " +
            "INNER JOIN product p ON sp.id_product = p.id_product " +
            "INNER JOIN `check` c ON s.check_number = c.check_number " +
            "WHERE p.product_name = ? " +
            "AND c.print_date BETWEEN ? AND ? " +
            "GROUP BY p.product_name";

    private static final String FIND_NOT_IN_STOCK_AND_NEVER_SOLD = "SELECT " +
            "p.id_product, p.category_number, p.product_name, p.characteristics, c.category_name" +
            " FROM product p " +
            "INNER JOIN Category c ON p.category_number = c.category_number " +
            "WHERE NOT EXISTS (" +
            "SELECT sp.id_product " +
            "FROM Store_Product sp " +
            "WHERE sp.id_product = p.id_product" +
            ") " +
            "AND NOT EXISTS (" +
            "SELECT sp.UPC " +
            "FROM sale s " +
            "INNER JOIN Store_Product sp ON s.UPC = sp.UPC " +
            "WHERE sp.id_product = p.id_product" +
            ")";

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
        return jdbcTemplate.query(FIND_PRODUCTS_BY_CATEGORY, new ProductRowMapper(), categoryId);
    }

    @Override
    public List<Product> findByName(String name) {
        String query = name + "%"; // Пошук часткового збігу
        return jdbcTemplate.query(FIND_PRODUCTS_BY_NAME, new Object[]{query}, new ProductRowMapper());
    }

    @Override
    public Optional<Integer> findTotalSalesByNameInDateRange(String productName, LocalDateTime startDate, LocalDateTime endDate) {
        return Optional.ofNullable(jdbcTemplate.query(FIND_SUM_OF_PRODUCT_SALES_BY_NAME_AND_DATES,
                new Object[]{productName, startDate, endDate},
                (rs, row) -> {
                    return rs.getInt(1);
                }).getFirst());
    }

    public List<Product> findNotInStoreNeverSoldProducts() {
        return jdbcTemplate.query(FIND_NOT_IN_STOCK_AND_NEVER_SOLD, new ProductRowMapper());
    }
}
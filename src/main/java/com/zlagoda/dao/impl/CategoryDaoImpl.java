package com.zlagoda.dao.impl;

import com.zlagoda.dao.CategoryDao;
import com.zlagoda.dao.mapper.CategoryRowMapper;
import com.zlagoda.entity.Category;
import com.zlagoda.dto.CategorySalesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String FIND_WITH_PRODUCTS_MORE_THAN_STATED = "SELECT c.category_number, " +
            "c.category_name, " +
            "COUNT(DISTINCT p.id_product) AS num_products_in_store " +
            "FROM category c " +
            "INNER JOIN product p ON c.category_number = p.category_number " +
            "INNER JOIN store_product sp ON p.id_product = sp.id_product " +
            "GROUP BY c.category_number, c.category_name " +
            "HAVING COUNT(DISTINCT sp.UPC) > ?;";

    private static final String FIND_TOTAL_SALES_BY_CATEGORY = """
            SELECT Category.category_number, Category.category_name, SUM(Sale.selling_price * Sale.product_number) AS total_sales
            FROM ((Sale INNER JOIN Store_Product ON Sale.UPC = Store_Product.UPC)
            INNER JOIN Product ON Store_Product.id_product = Product.id_product)
            INNER JOIN Category ON Product.category_number = Category.category_number
            GROUP BY Category.category_number, Category.category_name;
            """;

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

    public List<Category> findWithTotalProductsMoreThan(int quantity) {
        return jdbcTemplate.query(FIND_WITH_PRODUCTS_MORE_THAN_STATED, new Object[]{quantity}, new CategoryRowMapper());

    public List<CategorySalesDto> findTotalSalesByCategory() {
        return jdbcTemplate.query(FIND_TOTAL_SALES_BY_CATEGORY, new RowMapper<CategorySalesDto>() {
            @Override
            public CategorySalesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                CategorySalesDto categorySalesDto = new CategorySalesDto();
                categorySalesDto.setCategoryNumber(rs.getLong("category_number"));
                categorySalesDto.setCategoryName(rs.getString("category_name"));
                categorySalesDto.setTotalSales(rs.getBigDecimal("total_sales"));
                return categorySalesDto;
            }
        });
    }
}
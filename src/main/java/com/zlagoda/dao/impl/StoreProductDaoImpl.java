package com.zlagoda.dao.impl;

import com.zlagoda.dao.StoreProductDao;
import com.zlagoda.dao.mapper.StoreProductRowMapper;
import com.zlagoda.entity.StoreProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StoreProductDaoImpl implements StoreProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_STORE_PRODUCTS =
            "SELECT sp.*, p.id_product, p.product_name, p.characteristics, " +
                    "sp_upc.upc AS upc_prom, sp_upc.selling_price AS prom_price, sp_upc.products_number AS prom_quantity, sp_upc.promotional_product AS prom_isPromotional " +
                    "FROM Store_Product sp " +
                    "INNER JOIN Product p ON sp.id_product = p.id_product " +
                    "LEFT JOIN Store_Product sp_upc ON sp.upc_prom = sp_upc.upc " +
                    "ORDER BY sp.products_number";

    private static final String FIND_BY_ID =
            "SELECT sp.*, p.id_product, p.product_name, p.characteristics, " +
                    "sp_upc.upc AS upc_prom, sp_upc.selling_price AS prom_price, sp_upc.products_number AS prom_quantity, sp_upc.promotional_product AS prom_isPromotional " +
                    "FROM Store_Product sp " +
                    "INNER JOIN Product p ON sp.id_product = p.id_product " +
                    "LEFT JOIN Store_Product sp_upc ON sp.upc_prom = sp_upc.upc " +
                    "WHERE sp.upc = ?";

    private static final String INSERT_STORE_PRODUCT =
            "INSERT INTO Store_Product (UPC, UPC_PROM, id_product, selling_price, products_number, promotional_product) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_STORE_PRODUCT =
            "UPDATE Store_Product SET upc_prom = ?, id_product = ?, selling_price = ?, products_number = ?, promotional_product = ? WHERE upc = ?";

    private static final String DELETE_STORE_PRODUCT =
            "DELETE FROM Store_Product WHERE upc = ?";

    private static final String FIND_PROMOTIONAL_PRODUCTS =
            "SELECT sp.*, p.id_product, p.product_name, p.characteristics " +
                    "FROM Store_Product sp " +
                    "INNER JOIN Product p ON sp.id_product = p.id_product " +
                    "WHERE sp.promotional_product = true";

    private static final String FIND_STANDARD_PRODUCTS =
            "SELECT sp.*, p.id_product, p.product_name, p.characteristics, " +
                    "sp_upc.upc AS upc_prom, sp_upc.selling_price AS prom_price, sp_upc.products_number AS prom_quantity, sp_upc.promotional_product AS prom_isPromotional " +
                    "FROM Store_Product sp " +
                    "INNER JOIN Product p ON sp.id_product = p.id_product " +
                    "LEFT JOIN Store_Product sp_upc ON sp.upc_prom = sp_upc.upc " +
                    "WHERE sp.promotional_product = false AND sp.upc_prom IS NULL";

    private static final String UPDATE_QUANTITY = "UPDATE Store_Product SET products_number = products_number - ? WHERE upc = ?";

    @Override
    public List<StoreProduct> getAll() {
        return jdbcTemplate.query(FIND_ALL_STORE_PRODUCTS, new StoreProductRowMapper());
    }

    @Override
    public Optional<StoreProduct> getById(String upc) {
        List<StoreProduct> products = jdbcTemplate.query(FIND_BY_ID, new Object[]{upc}, new StoreProductRowMapper());
        return products.stream().findFirst();
    }

    @Override
    public void create(StoreProduct storeProduct) {
        Object[] params = {
                storeProduct.getUpc(),
                storeProduct.getUpcProm() != null ? storeProduct.getUpcProm().getUpc() : null,
                storeProduct.getProduct().getId(),
                storeProduct.getPrice(),
                storeProduct.getQuantity(),
                storeProduct.getIsPromotional()
        };

        jdbcTemplate.update(INSERT_STORE_PRODUCT, params);
    }

    // need some attention
    @Override
    public void update(StoreProduct storeProduct) {
        StoreProduct currentStoreProduct = getById(storeProduct.getUpc()).get();
        jdbcTemplate.update(UPDATE_STORE_PRODUCT,
                storeProduct.getUpcProm() != null ? storeProduct.getUpcProm().getUpc() : null,
                storeProduct.getProduct() != null ? storeProduct.getProduct().getId() : currentStoreProduct.getProduct().getId(),
                storeProduct.getPrice() != null ? storeProduct.getPrice() : currentStoreProduct.getPrice(),
                storeProduct.getQuantity() != null ? storeProduct.getQuantity() : currentStoreProduct.getQuantity(),
                storeProduct.getIsPromotional() != null ? storeProduct.getIsPromotional() : currentStoreProduct.getIsPromotional(),
                storeProduct.getUpc() != null ? storeProduct.getUpc() : currentStoreProduct.getUpc());
    }

    @Override
    public void delete(String upc) {
        jdbcTemplate.update(DELETE_STORE_PRODUCT, upc);
    }

    @Override
    public List<StoreProduct> getPromotionalProducts() {
        return jdbcTemplate.query(FIND_PROMOTIONAL_PRODUCTS, new StoreProductRowMapper());
    }

    @Override
    public List<StoreProduct> getStandardProducts() {
        return jdbcTemplate.query(FIND_STANDARD_PRODUCTS, new StoreProductRowMapper());
    }

    @Override
    public void updateProductQuantity(String upc, int quantity) {
        jdbcTemplate.update(UPDATE_QUANTITY, quantity, upc);
    }
}

package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Product;
import com.zlagoda.entity.StoreProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreProductRowMapper implements RowMapper<StoreProduct> {

    @Override
    public StoreProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = Product.builder()
                .id(rs.getLong("id_product"))
                .name(rs.getString("product_name"))
                .characteristics(rs.getString("characteristics"))
                .build();

        StoreProduct upcProm = rs.getString("upc_prom") != null ? StoreProduct.builder()
                .upc(rs.getString("upc_prom"))
                .price(rs.getDouble("prom_price"))
                .quantity(rs.getInt("prom_quantity"))
                .isPromotional(rs.getBoolean("prom_isPromotional"))
                .build() : null;

        return StoreProduct.builder()
                .upc(rs.getString("upc"))
                .upcProm(upcProm)
                .product(product)
                .price(rs.getDouble("selling_price"))
                .quantity(rs.getInt("products_number"))
                .isPromotional(rs.getBoolean("promotional_product"))
                .build();
    }
}

package com.zlagoda.dao;

import com.zlagoda.entity.StoreProduct;

import java.util.List;
import java.util.Optional;

public interface StoreProductDao extends GenericDao<StoreProduct, String> {
    List<StoreProduct> getPromotionalProducts();
    List<StoreProduct> getStandardProducts();
    Optional<StoreProduct> findByUpcProm(String prom_upc);
    void subtractAmountByUpc(String upc, int delta);

}
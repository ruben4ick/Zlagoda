package com.zlagoda.dao;

import com.zlagoda.entity.StoreProduct;

import java.util.List;

public interface StoreProductDao extends GenericDao<StoreProduct, String> {
    List<StoreProduct> getPromotionalProducts();
}
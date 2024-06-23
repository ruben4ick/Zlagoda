package com.zlagoda.dao;

import com.zlagoda.dto.CategorySalesDto;
import com.zlagoda.entity.Category;
import com.zlagoda.entity.Employee;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, Long> {
    List<Category> findWithTotalProductsMoreThan(int quantity);
    
    List<CategorySalesDto> findTotalSalesByCategory();

}

package com.zlagoda.service;


import com.zlagoda.dao.GenericDao;
import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.CategorySalesDto;
import com.zlagoda.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends GenericService<CategoryDto, Long> {

    List<CategorySalesDto> findTotalSalesByCategory();

}

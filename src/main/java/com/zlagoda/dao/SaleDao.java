package com.zlagoda.dao;

import org.modelmapper.internal.Pair;
import com.zlagoda.entity.Sale;

import java.util.List;

public interface SaleDao extends GenericDao<Sale, Pair<String, String>>{
    List<Sale> getByCheck(String check_number);
}

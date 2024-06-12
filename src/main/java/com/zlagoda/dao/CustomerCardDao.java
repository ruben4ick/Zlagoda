package com.zlagoda.dao;

import com.zlagoda.entity.CustomerCard;

import java.util.List;


public interface CustomerCardDao extends GenericDao<CustomerCard, String>{
    List<CustomerCard> findByPercent(int percent);
}

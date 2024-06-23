package com.zlagoda.dao;

import com.zlagoda.dto.CheckDto;
import com.zlagoda.entity.Check;

import java.util.List;

public interface CheckDao  extends GenericDao<Check, String>{
    List<Check> getByEmplId(String id);
}

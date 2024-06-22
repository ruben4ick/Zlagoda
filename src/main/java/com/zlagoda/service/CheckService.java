package com.zlagoda.service;

import com.zlagoda.dto.CheckDto;
import com.zlagoda.entity.Check;

import java.util.List;

public interface CheckService extends GenericService<CheckDto, String> {
    List<CheckDto> getByEmplId(String id);
    List<CheckDto> getByEmplSurname(String surname);
}

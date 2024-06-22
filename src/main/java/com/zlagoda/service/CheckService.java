package com.zlagoda.service;

import com.zlagoda.dto.CheckDto;
import com.zlagoda.entity.Check;

import java.math.BigDecimal;
import java.util.List;

public interface CheckService extends GenericService<CheckDto, String> {
    List<CheckDto> getByEmplId(String id);
    List<CheckDto> getByEmplSurname(String surname);

    BigDecimal sumOfChecks(List<CheckDto> checks);
}

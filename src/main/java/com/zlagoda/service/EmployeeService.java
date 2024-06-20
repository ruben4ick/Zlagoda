package com.zlagoda.service;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends GenericService<EmployeeDto, String> {
    List<Employee> getAllCashiers();

    List<EmployeeDto> findContactDetailsBySurname(String surname);

}

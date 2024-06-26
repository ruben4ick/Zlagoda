package com.zlagoda.service;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends GenericService<EmployeeDto, String> {
    List<EmployeeDto> getAllCashiers();

    List<EmployeeDto> getBySurname(String surname);

    List<EmployeeDto> findContactDetailsBySurname(String surname);

    List<Employee.Role> getEmployeeRoles();

    Optional<EmployeeDto> findByUsername(String username);
  
    List<EmployeeDto> getAllCashiersServedAllCustomers();

}

package com.zlagoda.service;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void saveEmployee(EmployeeDto employeeDto);

    void deleteEmployee(String employeeId);

    void updateEmployee(EmployeeDto employeeDto);
    Optional<Employee> getEmployeeById(String employeeId);



}

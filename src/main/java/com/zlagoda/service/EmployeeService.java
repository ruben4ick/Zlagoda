package com.zlagoda.service;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(final String employeeId);

    void updateEmployeeById(Employee employee);


    void deleteEmployee(final String employeeId);



}

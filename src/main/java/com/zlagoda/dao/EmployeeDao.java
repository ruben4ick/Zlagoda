package com.zlagoda.dao;


import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> findByName(final String name);

    List<Employee> findAllEmployees();

    Optional<Employee> findById(final String employeeId);

    void updateById(final String employeeId, final Employee employee);

    void saveEmployee(final String employeeId , final Employee employee);

    void deleteEmployee(final String employeeId);

}
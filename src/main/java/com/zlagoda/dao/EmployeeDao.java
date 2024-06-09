package com.zlagoda.dao;


import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao  {


    List<Employee> findAll();

    Optional<Employee> findById(String employeeId);

    //void update(final String employeeId, final Employee employee);

    void create(final Employee employee);

    void deleteEmployee(final String employeeId);

    void updateEmployee(Employee employee);
}
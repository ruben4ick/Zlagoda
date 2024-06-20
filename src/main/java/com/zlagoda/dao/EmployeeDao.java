package com.zlagoda.dao;


import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, String> {

    List<Employee> getAllCashiers();
    List<Employee> findContactDetailsBySurname(String surname);

}
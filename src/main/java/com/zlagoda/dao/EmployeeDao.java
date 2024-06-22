package com.zlagoda.dao;


import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao extends GenericDao<Employee, String> {

    List<Employee> getAllCashiers();
    List<Employee> getBySurname(String surname);
    List<Employee> findContactDetailsBySurname(String surname);

}
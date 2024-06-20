package com.zlagoda.service.impl;

import com.zlagoda.converter.EmployeeConverter;
import com.zlagoda.dao.EmployeeDao;
import com.zlagoda.dao.mapper.EmployeeContactRowMapper;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeConverter employeeConverter;

    public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeConverter employeeConverter) {
        this.employeeDao = employeeDao;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeDao.getAll().stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();
    }

    @Override
    public List<Employee> getAllCashiers() {
        return employeeDao.getAllCashiers();
    }

    @Override
    public void create(final EmployeeDto employeeDto) {
        Employee employee = employeeConverter.mapToEmployee(employeeDto);
        employeeDao.create(employee);
    }

    @Override
    public void delete(String employeeId) {
        employeeDao.delete(employeeId);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.mapToEmployee(employeeDto);
        employeeDao.update(employee);
    }

    @Override
    public Optional<EmployeeDto> getById(String employeeId) {
        return employeeDao.getById(employeeId)
                .map(employeeConverter::mapToEmployeeDto);
    }

    @Override
    public List<EmployeeDto> findContactDetailsBySurname(String surname) {
        return employeeDao.findContactDetailsBySurname(surname).stream()
                .map(employeeConverter::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllCashiersServedAllCustomers() {
        return employeeDao.getAllCashiersServedAllCustomers().stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();
    }

}

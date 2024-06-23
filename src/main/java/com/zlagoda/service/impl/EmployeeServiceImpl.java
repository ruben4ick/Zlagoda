package com.zlagoda.service.impl;

import com.zlagoda.converter.EmployeeConverter;
import com.zlagoda.dao.EmployeeDao;
import com.zlagoda.dao.mapper.EmployeeContactRowMapper;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final EmployeeConverter employeeConverter;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeDao employeeDao, EmployeeConverter employeeConverter, PasswordEncoder passwordEncoder) {
        this.employeeDao = employeeDao;
        this.employeeConverter = employeeConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeDao.getAll().stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();
    }

    @Override
    public List<EmployeeDto> getAllCashiers() {
        return employeeDao.getAllCashiers().stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();
    }

    @Override
    public List<EmployeeDto> getBySurname(String surname) {
        surname = surname.trim();
        return employeeDao.getBySurname(surname).stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();
    }

    @Override
    public void create(final EmployeeDto employeeDto) {
        employeeDto.normalize();
        Employee employee = employeeConverter.mapToEmployee(employeeDto);
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employeeDao.create(employee);
    }

    @Override
    public void delete(String employeeId) {
        employeeDao.delete(employeeId);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        employeeDto.normalize();
        Employee employee = employeeConverter.mapToEmployee(employeeDto);
        employeeDao.update(employee);
    }

    @Override
    public Optional<EmployeeDto> getById(String employeeId) {
        employeeId = employeeId.trim();
        return employeeDao.getById(employeeId)
                .map(employeeConverter::mapToEmployeeDto);
    }


    @Override
    public List<EmployeeDto> findContactDetailsBySurname(String surname) {
        surname = surname.trim();
        return employeeDao.findContactDetailsBySurname(surname).stream()
                .map(employeeConverter::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override

    public List<Employee.Role> getEmployeeRoles() {
        return List.of(Employee.Role.values());
    }

    @Override
    public Optional<EmployeeDto> findByUsername(String username) {
        return employeeDao.findByUsername(username)
                .map(employeeConverter::mapToEmployeeDto);
    }

    public List<EmployeeDto> getAllCashiersServedAllCustomers() {
        return employeeDao.getAllCashiersServedAllCustomers().stream()
                .map(employeeConverter::mapToEmployeeDto)
                .toList();

    }

}

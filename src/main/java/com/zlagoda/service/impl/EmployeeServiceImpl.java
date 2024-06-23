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
    public List<Employee> getAllCashiers() {
        return employeeDao.getAllCashiers();
    }

    @Override
    public void create(final EmployeeDto employeeDto) {
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
    public List<Employee.Role> getEmployeeRoles() {
        return List.of(Employee.Role.values());
    }


    /*private Employee setOnlyPresentFields(final Employee oldEmployee, final Employee newEmployee) {
        String name = newEmployee.getName();
        String surname = newEmployee.getSurname();
        //String password = newEmployee.getPassword();
        String patronymic = newEmployee.getPatronymic();
        Employee.Role role = newEmployee.getRole();
        BigDecimal salary = newEmployee.getSalary();
        Date birthdate = newEmployee.getBirthDate();
        Date startDate = newEmployee.getStartDate();
        String phoneNumber = newEmployee.getPhoneNumber();
        String city = newEmployee.getCity();
        String street = newEmployee.getStreet();
        String zipCode = newEmployee.getZipCode();

        if (name.length() > 0) {
            oldEmployee.setName(name);
        }
        if (surname.length() > 0) {
            oldEmployee.setSurname(surname);
        }
        *//*if (password.length() > 0) {
            oldEmployee.setPassword(password);
        }*//*
        if (patronymic.length() > 0) {
            oldEmployee.setPatronymic(patronymic);
        }
        if (role != null) {
            oldEmployee.setRole(role);
        }
        if (salary != null) {
            oldEmployee.setSalary(salary);
        }
        if (birthdate != null) {
            oldEmployee.setBirthDate(birthdate);
        }
        if (startDate != null) {
            oldEmployee.setStartDate(startDate);
        }
        if (phoneNumber.length() > 0) {
            oldEmployee.setPhoneNumber(phoneNumber);
        }
        if (city.length() > 0) {
            oldEmployee.setCity(city);
        }
        if (street.length() > 0) {
            oldEmployee.setStreet(street);
        }
        if (zipCode.length() > 0) {
            oldEmployee.setZipCode(zipCode);
        }
        return oldEmployee;
    }*/

    /*@Override
    public Employee getCurrent() {
        EmployeeDetails employeeDetails = (EmployeeDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee employee = employeeDetails.getEmployee();
        return employee;
    }

    @Override
    public List<EmployeeStatisticDTO> getEmployeeStats() {
        return employeeDAO.getEmployeeStats();
    }*/
}

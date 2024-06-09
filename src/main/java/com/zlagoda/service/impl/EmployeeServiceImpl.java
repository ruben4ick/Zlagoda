package com.zlagoda.service.impl;

import com.zlagoda.dao.EmployeeDao;
import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;


    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public void saveEmployee(final Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeDao.findById(employeeId);
    }

  /*  public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEmployee(employeeDto);
        employeeDao.
    }*/

    private Employee mapToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .surname(employeeDto.getSurname())
                .name(employeeDto.getName())
                .patronymic(employeeDto.getPatronymic())
                .role(employeeDto.getRole())
                .salary(employeeDto.getSalary())
                .birthDate(employeeDto.getBirthDate())
                .startDate(employeeDto.getStartDate())
                .phoneNumber(employeeDto.getPhoneNumber())
                .city(employeeDto.getCity())
                .street(employeeDto.getStreet())
                .zipCode(employeeDto.getZipCode())
                .build();
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

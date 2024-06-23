package com.zlagoda.converter;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmployeeConverter {

    public Employee mapToEmployee(EmployeeDto employeeDto) {
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
                .username(employeeDto.getUsername())
                .password(employeeDto.getPassword())
                .build();
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .name(employee.getName())
                .patronymic(employee.getPatronymic())
                .role(employee.getRole())
                .salary(employee.getSalary())
                .birthDate(employee.getBirthDate())
                .startDate(employee.getStartDate())
                .phoneNumber(employee.getPhoneNumber())
                .city(employee.getCity())
                .street(employee.getStreet())
                .zipCode(employee.getZipCode())
                .username(employee.getUsername())
                .password(employee.getPassword())
                .build();
    }
}

package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Employee;
import com.zlagoda.entity.Employee.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    private static final String ID_EMPLOYEE = "id_employee";
    private static final String SURNAME = "empl_surname";
    private static final String NAME = "empl_name";
    private static final String ROLE = "empl_role";
    private static final String PATRONYMIC = "empl_patronymic";
    private static final String SALARY = "salary";
    private static final String BIRTH_DATE = "date_of_birth";
    private static final String START_DATE = "date_of_start";
    private static final String PHONE = "phone_number";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ZIP_CODE = "zip_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setId(rs.getString(ID_EMPLOYEE));
        employee.setSurname(rs.getString(SURNAME));
        employee.setName(rs.getString(NAME));
        employee.setPatronymic(rs.getString(PATRONYMIC));
        employee.setRole(Role.valueOf(rs.getString(ROLE)));
        employee.setSalary(rs.getBigDecimal(SALARY));
        employee.setStartDate(rs.getDate(START_DATE));
        employee.setBirthDate(rs.getDate(BIRTH_DATE));
        employee.setPhoneNumber(rs.getString(PHONE));
        employee.setCity(rs.getString(CITY));
        employee.setStreet(rs.getString(STREET));
        employee.setZipCode(rs.getString(ZIP_CODE));
        employee.setUsername(rs.getString(USERNAME));
        employee.setPassword(rs.getString(PASSWORD));
        return employee;
    }
}
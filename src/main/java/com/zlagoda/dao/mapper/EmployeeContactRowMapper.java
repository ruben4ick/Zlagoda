package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeContactRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setSurname(rs.getString("empl_surname"));
        employee.setPhoneNumber(rs.getString("phone_number"));
        employee.setCity(rs.getString("city"));
        employee.setStreet(rs.getString("street"));
        employee.setZipCode(rs.getString("zip_code"));
        return employee;
    }
}
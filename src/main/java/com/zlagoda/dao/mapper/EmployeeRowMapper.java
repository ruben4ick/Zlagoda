package com.zlagoda.dao.mapper;

import com.zlagoda.entity.Employee;
import com.zlagoda.entity.Employee.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setId(rs.getLong("id"));
        employee.setSurname(rs.getString("surname"));
        employee.setName(rs.getString("name"));
        employee.setPatronymic(rs.getString("patronymic"));
        employee.setRole(Role.valueOf(rs.getString("role")));
        employee.setSalary(rs.getDouble("salary"));
        employee.setStartDate(rs.getDate("start_date"));
        employee.setBirthDate(rs.getDate("birth_date"));
        employee.setPhoneNumber(rs.getString("phone_number"));
        employee.setCity(rs.getString("city"));
        employee.setStreet(rs.getString("street"));
        employee.setZip_code(rs.getString("zip_code"));

        return employee;
    }
}
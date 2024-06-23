package com.zlagoda.dao.impl;

import com.zlagoda.dao.EmployeeDao;
import com.zlagoda.dao.mapper.EmployeeContactRowMapper;
import com.zlagoda.dao.mapper.EmployeeRowMapper;
import com.zlagoda.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String FIND_BY_ID = "SELECT * FROM `Employee` WHERE id_employee = ?";
    private static final String FIND_ALL_EMPLOYEES = "SELECT * FROM `Employee` ORDER BY empl_surname";
    private static final String FIND_ALL_CASHIERS = "SELECT * FROM Employee WHERE empl_role = 'CASHIER' ORDER BY empl_surname";

    private static final String EMPLOYEE_CREATED_CHECK_AMOUNT_AND_SUM =
            "SELECT Employee.id_employee,\n" +
                    "Employee.empl_name,\n" +
                    "Employee.empl_surname,\n" +
                    "Employee.empl_patronymic,\n" +
                    "COUNT(ch.check_number) AS checks_count,\n" +
                    "COALESCE(SUM(ch.sum_total + ch.vat), 0) AS total_sum\n" +
                    "FROM Employee\n" +
                    "LEFT JOIN `Check` AS ch ON ch.id_employee = Employee.id_employee\n" +
                    "WHERE Employee.`role` = \"CASHIER\"\n" +
                    "GROUP BY Employee.id_employee";

    private static final String UPDATE = "UPDATE Employee\n" +
            "SET empl_surname = ?,\n" +
            "empl_name = ?,\n" +
            "empl_patronymic = ?,\n" +
            "empl_role = ?,\n" +
            "salary = ?,\n" +
            "date_of_birth = ?,\n" +
            "date_of_start = ?,\n" +
            "phone_number = ?,\n" +
            "city = ?,\n" +
            "street = ?,\n" +
            "zip_code = ?,\n" +
            "username = ?,\n" +
            "password = ?\n" +
            "WHERE id_employee = ?";

    private static final String INSERT_EMPLOYEE = "INSERT INTO Employee (id_employee, empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, " +
            "date_of_start, phone_number, city, street, zip_code, username, password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE id_employee = ?";

    private static final String FIND_BY_USERNAME = "SELECT * FROM Employee WHERE username = ?";
    private static final String FIND_CONTACT_DETAILS_BY_SURNAME = "SELECT empl_surname, empl_name, empl_patronymic, phone_number, city, street, zip_code FROM Employee WHERE empl_surname = ?";
    private static final String FIND_BY_SURNAME = "SELECT * FROM Employee WHERE empl_surname = ?";

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> getAllCashiers() {
        return jdbcTemplate.query(FIND_ALL_CASHIERS, new EmployeeRowMapper());
    }

    @Override
    public List<Employee> getBySurname(String surname) {
        return jdbcTemplate.query(FIND_BY_SURNAME, new EmployeeRowMapper(), surname);
    }

    @Override
    public List<Employee> findContactDetailsBySurname(String surname) {
        return jdbcTemplate.query(FIND_CONTACT_DETAILS_BY_SURNAME, new Object[]{surname}, new EmployeeContactRowMapper());
    }

    @Override
    public Optional<Employee> getById(String employeeId) {
        List<Employee> employees = jdbcTemplate.query(FIND_BY_ID, new Object[]{employeeId}, new EmployeeRowMapper());
        return employees.stream().findFirst();
    }

    @Override
    public void create(final Employee employee) {
        String generatedId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        employee.setId(generatedId);
        Object[] params = {
                employee.getId(),
                employee.getSurname(),
                employee.getName(),
                // passwordEncoder.encode(employee.getPassword()),
                employee.getPatronymic(),
                employee.getRole().name(),
                employee.getSalary(),
                employee.getBirthDate(),
                employee.getStartDate(),
                employee.getPhoneNumber(),
                employee.getCity(),
                employee.getStreet(),
                employee.getZipCode(),
                employee.getUsername(),
                employee.getPassword()
        };
        jdbcTemplate.update(INSERT_EMPLOYEE, params);
    }

    @Override
    public void delete(final String employeeId) {
        jdbcTemplate.update(DELETE_EMPLOYEE, employeeId);
    }

    @Override
    public void update(Employee employee) {
        Object[] params = {
                employee.getSurname(),
                employee.getName(),
                // employee.getPassword().charAt(0) == '$' ? employee.getPassword() : passwordEncoder.encode(employee.getPassword()),
                employee.getPatronymic(),
                employee.getRole().name(),
                employee.getSalary(),
                employee.getBirthDate(),
                employee.getStartDate(),
                employee.getPhoneNumber(),
                employee.getCity(),
                employee.getStreet(),
                employee.getZipCode(),
                employee.getUsername(),
                employee.getPassword(),
                employee.getId()
        };
        jdbcTemplate.update(UPDATE, params);
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        List<Employee> employee = jdbcTemplate.query(FIND_BY_USERNAME, new Object[] {username}, new EmployeeRowMapper());
        return employee.stream().findFirst();
    }
}

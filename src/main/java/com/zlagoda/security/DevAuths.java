package com.zlagoda.security;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Component
public class DevAuths {
    private EmployeeService employeeService;

    @Autowired
    public DevAuths(EmployeeService employeeDao) {
        this.employeeService = employeeDao;
    }

    @EventListener(value = ApplicationReadyEvent.class)
    public void createTestEmployees() {
        if (employeeService.findContactDetailsBySurname("Admin").isPresent())
            return;

        if (employeeService.findContactDetailsBySurname("Cashier").isPresent())
            return;

        EmployeeDto manager = new EmployeeDto();
        manager.setUsername("admin");
        manager.setPassword("admin");
        manager.setSurname("Admin");
        manager.setName("Admin");
        manager.setPatronymic("Adminovych");
        manager.setRole(Employee.Role.MANAGER);
        manager.setSalary(BigDecimal.valueOf(7777777));
        manager.setStartDate(Date.valueOf("2001-12-12"));
        manager.setBirthDate(Date.valueOf("1970-1-1"));
        manager.setPhoneNumber("380777777777");
        manager.setCity("Kyiv");
        manager.setStreet("Streer");
        manager.setZipCode("77777");
        employeeService.create(manager);
        EmployeeDto cashier = new EmployeeDto();
        cashier.setUsername("cashier");
        cashier.setPassword("cashier");
        cashier.setSurname("Cashier");
        cashier.setName("Cashier");
        cashier.setPatronymic("Cashierovych");
        cashier.setRole(Employee.Role.CASHIER);
        cashier.setSalary(BigDecimal.valueOf(777));
        cashier.setStartDate(Date.valueOf("2012-12-12"));
        cashier.setBirthDate(Date.valueOf("1980-1-1"));
        cashier.setPhoneNumber("380111111111");
        cashier.setCity("Berdychyv");
        cashier.setStreet("Street(");
        cashier.setZipCode("101010");
        employeeService.create(cashier);
    }
}

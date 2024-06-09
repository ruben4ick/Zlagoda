package com.zlagoda.controller;

import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("employee/add")
    public String employeeAdd(Model model) {
        model.addAttribute("roles", Employee.Role.values());
        model.addAttribute("employee", new Employee());
        return "employees-add";
    }

    @PostMapping("employee/add")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("employee/delete/{employeeId}")
    public String employeeDelete(@PathVariable("employeeId") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }

    /*@PostMapping("employee/{employeeId}/edit")
    public String employeeEdit(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {*/
}

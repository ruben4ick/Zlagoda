package com.zlagoda.controller;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.entity.Employee;
import com.zlagoda.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<EmployeeDto> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee/employees";
    }

    @GetMapping("/cashiers")
    public String cashiers(Model model) {
        List<Employee> cashiers = employeeService.getAllCashiers();
        model.addAttribute("employees", cashiers);
        return "employee/cashiers";
    }

    @GetMapping("/employees/contact")
    public String getEmployeeContactDetails(@RequestParam("surname") String surname, Model model) {
        List<EmployeeDto> employees = employeeService.findContactDetailsBySurname(surname);
        model.addAttribute("employees", employees);
        return "employee/employees-contact";
    }

    @GetMapping("employees/add")
    public String employeeAdd(Model model) {
        model.addAttribute("roles", Employee.Role.values());
        model.addAttribute("employee", new Employee());
        return "employee/employees-add";
    }

    @PostMapping("employees/add")
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDto employeeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", employeeDto);
            model.addAttribute("roles", Employee.Role.values());
            return "employee/employees-add";
        }
        employeeService.create(employeeDto);
        return "redirect:/employees";
    }

    @GetMapping("employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") String employeeId) {
        employeeService.delete(employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") String employeeId, Model model) {
        Optional<EmployeeDto> employeeOpt = employeeService.getById(employeeId);

        if (employeeOpt.isPresent()) {
            model.addAttribute("employee", employeeOpt.get());
            model.addAttribute("roles", Employee.Role.values());
            return "employee/employees-edit";
        } else {
            // Redirect or handle the case when the employee does not exist
            return "redirect:/employees";
        }
    }
    @PostMapping("/employees/edit/{employeeId}")
    public String editEmployee(@PathVariable("employeeId") String employeeId, @ModelAttribute("employee") EmployeeDto employee, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("roles", Employee.Role.values());
            return "employee/employees-edit";
        }
        employee.setId(employeeId);
        employeeService.update(employee);
        return "redirect:/employees";
    }
}

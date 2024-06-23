package com.zlagoda.controller;

import com.zlagoda.dto.EmployeeDto;
import com.zlagoda.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EmployeeService employeeService;
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<EmployeeDto> employeeDto = employeeService.findByUsername(auth.getName());
        model.addAttribute("logged_name", employeeDto.get().getName());
        return "home";
    }
    @GetMapping("/home")
    public String homeRedirect(){
        return "redirect:/";
    }
}

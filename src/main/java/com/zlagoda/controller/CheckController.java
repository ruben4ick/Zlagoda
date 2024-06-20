package com.zlagoda.controller;

import com.zlagoda.dto.*;
import com.zlagoda.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checks")
public class CheckController {

    private final CheckService checkService;
    private final SaleService saleService;
    private final EmployeeService employeeService;
    private final CustomerCardService customerCardService;
    private final StoreProductService storeProductService;
    private final ProductService productService;

    @GetMapping
    public String listChecks(Model model) {
        List<CheckDto> checks = checkService.getAll();
        model.addAttribute("checks", checks);
        return "check/checks";
    }

    @GetMapping("/add")
    public String addCheck(Model model) {
        List<EmployeeDto> employees = employeeService.getAll();
        List<CustomerCardDto> customerCards = customerCardService.getAll();
        List<StoreProductDto> storeProducts = storeProductService.getAll();

        model.addAttribute("employees", employees);
        model.addAttribute("customerCards", customerCards);
        model.addAttribute("storeProducts", storeProducts);
        model.addAttribute("check", new CheckDto());
        return "check/checks-add";
    }

    @PostMapping("/add")
    public String saveCheck(@Valid @ModelAttribute("check") CheckDto checkDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<EmployeeDto> employees = employeeService.getAll();
            List<CustomerCardDto> customerCards = customerCardService.getAll();
            List<StoreProductDto> storeProducts = storeProductService.getAll();
            model.addAttribute("employees", employees);
            model.addAttribute("storeProducts", storeProducts);
            model.addAttribute("customerCards", customerCards);
            return "check/checks-add";
        }
        checkService.create(checkDto);

        return "redirect:/checks";
    }


    @GetMapping("/delete/{checkNumber}")
    public String deleteCheck(@PathVariable("checkNumber") String checkNumber) {
        checkService.delete(checkNumber);
        return "redirect:/checks";
    }
}

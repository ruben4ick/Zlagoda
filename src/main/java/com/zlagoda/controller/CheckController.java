package com.zlagoda.controller;

import com.zlagoda.dto.*;
import com.zlagoda.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        BigDecimal sum = checkService.sumOfChecks(checks);
        model.addAttribute("checks", checks);
        model.addAttribute("sum_of_checks", sum);
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

    @GetMapping("/check-id")
    public String getSaleInfoByCheck(@RequestParam(value = "check_number", required = false, defaultValue = "null")
                                         String check_number, Model model){
        if (check_number.equals("null"))
            return "check/sale-info";

        List<SaleDto> sales = saleService.getByCheck(check_number);
        List<String> product_names = new ArrayList<>();
        for(SaleDto sale : sales){
            Optional<StoreProductDto> storeProduct = storeProductService.getById(sale.getStoreProduct().getUpc());
            product_names.add(storeProduct.get().getProduct().getName());
        }
        model.addAttribute("sales", sales);
        model.addAttribute("product_names", product_names);
        model.addAttribute("size", sales.size());
        return "check/sale-info";
    }

    @GetMapping("/empl-id")
    public String getChecksByEmployeeSurname(@RequestParam(value = "employee_surname", required = false, defaultValue = "null")
                                                 String employee_surname, Model model){
        if (employee_surname.equals("null")){
            return "check/checks";
        }
        model.addAttribute("checks", checkService.getByEmplSurname(employee_surname));
        return "check/checks";
    }

    @GetMapping("/date-range")
    public String getChecksByDateRange(@RequestParam("start") String start,
                                       @RequestParam("end") String end,
                                       Model model) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<CheckDto> allChecks = checkService.getAll();
        List<CheckDto> checks = checkService.selectByDate(allChecks, startDate, endDate);
        model.addAttribute("checks", checks);
        return "check/checks";
    }
}

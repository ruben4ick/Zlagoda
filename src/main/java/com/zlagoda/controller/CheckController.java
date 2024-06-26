package com.zlagoda.controller;

import com.zlagoda.dto.*;
import com.zlagoda.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<EmployeeDto> employee = employeeService.findByUsername(auth.getName());
        List<CustomerCardDto> customerCards = customerCardService.getAll();
        List<StoreProductDto> storeProducts = storeProductService.getAll();

        model.addAttribute("employee", employee.get());
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

        if (sales.isEmpty())
            return "check/checks";

        List<String> product_names = new ArrayList<>();
        for(SaleDto sale : sales){
            Optional<StoreProductDto> storeProduct = storeProductService.getById(sale.getStoreProduct().getUpc());
            storeProduct.ifPresent(storeProductDto -> product_names.add(storeProductDto.getProduct().getName()));
        }
        model.addAttribute("sales", sales);
        model.addAttribute("product_names", product_names);
        model.addAttribute("size", sales.size());
        return "check/sale-info";
    }

    @GetMapping("/empl-id")
    public String getChecksByEmployeeSurname(@RequestParam(value = "employee_surname", required = false, defaultValue = "null") String employee_surname,
                                             @RequestParam(value = "start", required = false) String start,
                                             @RequestParam(value = "end", required = false) String end,
                                             Model model){
        if (employee_surname.equals("null")){
            return "check/checks";
        }

        List<CheckDto> checks;
        if (start != null && !start.isEmpty() && end != null && !end.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);
            List<CheckDto> allChecks = checkService.getByEmplSurname(employee_surname);
            checks = checkService.selectByDate(allChecks, startDate, endDate);
        } else if ((start == null || start.isEmpty()) && end != null && !end.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.MIN;
            LocalDateTime endDate = LocalDateTime.parse(end);
            List<CheckDto> allChecks = checkService.getByEmplSurname(employee_surname);
            checks = checkService.selectByDate(allChecks, startDate, endDate);
        } else if (start != null && !start.isEmpty() && (end == null || end.isEmpty())) {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.MAX;
            List<CheckDto> allChecks = checkService.getByEmplSurname(employee_surname);
            checks = checkService.selectByDate(allChecks, startDate, endDate);
        } else {
            checks = checkService.getByEmplSurname(employee_surname);
        }
        BigDecimal sum = checkService.sumOfChecks(checks);
        model.addAttribute("checks", checks);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("sum_of_checks", sum);
        return "check/checks";
    }

    @GetMapping("/date-range")
    public String getChecksByDateRange(@RequestParam(value = "start", required = false) String start,
                                       @RequestParam(value = "end", required = false) String end,
                                       Model model) {
        LocalDateTime startDate = (start != null && !start.isEmpty()) ? LocalDateTime.parse(start) : LocalDateTime.MIN;
        LocalDateTime endDate = (end != null && !end.isEmpty()) ? LocalDateTime.parse(end) : LocalDateTime.MAX;

        List<CheckDto> allChecks = checkService.getAll();
        List<CheckDto> checks = checkService.selectByDate(allChecks, startDate, endDate);
        BigDecimal sum = checkService.sumOfChecks(checks);
        model.addAttribute("checks", checks);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("sum_of_checks", sum);
        return "check/checks";
    }
}

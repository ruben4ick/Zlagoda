package com.zlagoda.controller;

import com.zlagoda.dto.CustomerCardDto;
import com.zlagoda.entity.CustomerCard;
import com.zlagoda.service.CustomerCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer-cards")
public class CustomerCardController {

    private final CustomerCardService customerCardService;

    @Autowired
    public CustomerCardController(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @GetMapping
    public String customerCards(Model model) {
        List<CustomerCardDto> customerCards = customerCardService.getAll();
        model.addAttribute("customerCards", customerCards);
        return "customer-card/customer-cards";
    }

    @GetMapping("/add")
    public String customerCardAdd(Model model) {
        model.addAttribute("customerCard", new CustomerCard());
        return "customer-card/customer-cards-add";
    }

    @PostMapping("/add")
    public String saveCustomerCard(@Valid @ModelAttribute("customerCard") CustomerCardDto customerCardDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerCard", customerCardDto);
            return "customer-card/customer-cards-add";
        }
        customerCardService.create(customerCardDto);
        return "redirect:/customer-cards";
    }

    @GetMapping("/edit/{cardNumber}")
    public String editCustomerCard(@PathVariable("cardNumber") String cardNumber, Model model) {
        Optional<CustomerCardDto> customerCardOpt = customerCardService.getById(cardNumber);

        if (customerCardOpt.isPresent()) {
            model.addAttribute("customerCard", customerCardOpt.get());
            return "customer-card/customer-cards-edit";
        } else {
            return "redirect:/customer-cards";
        }
    }

    @PostMapping("/edit/{cardNumber}")
    public String editCustomerCard(@PathVariable("cardNumber") String cardNumber, @ModelAttribute("customerCard") CustomerCardDto customerCard, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerCard", customerCard);
            return "customer-card/customer-cards-edit";
        }
        customerCard.setCardNumber(cardNumber);
        customerCardService.update(customerCard);
        return "redirect:/customer-cards";
    }

    @GetMapping("/delete/{cardNumber}")
    public String deleteCustomerCard(@PathVariable("cardNumber") String cardNumber) {
        customerCardService.delete(cardNumber);
        return "redirect:/customer-cards";
    }
}
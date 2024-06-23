package com.zlagoda.controller;

import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.CategorySalesDto;
import com.zlagoda.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categories(Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "category/categories";
    }

    @GetMapping("/add")
    public String categoryAdd(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "category/categories-add";
    }

    @PostMapping("/add")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", categoryDto);
            return "category/categories-add";
        }
        categoryService.create(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{categoryNumber}")
    public String editCategory(@PathVariable("categoryNumber") Long categoryNumber, Model model) {
        Optional<CategoryDto> categoryOpt = categoryService.getById(categoryNumber);

        if (categoryOpt.isPresent()) {
            model.addAttribute("category", categoryOpt.get());
            return "category/categories-edit";
        } else {
            return "redirect:/categories";
        }
    }

    @PostMapping("/edit/{categoryNumber}")
    public String editCategory(@PathVariable("categoryNumber") Long categoryNumber, @Valid @ModelAttribute("category") CategoryDto category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "category/categories-edit";
        }
        category.setNumber(categoryNumber);
        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{categoryNumber}")
    public String deleteCategory(@PathVariable("categoryNumber") Long categoryNumber) {
        categoryService.delete(categoryNumber);
        return "redirect:/categories";
    }


    @GetMapping("/moreThan")
    public String moreThan(@RequestParam(value = "prodQuantity") int quantity, Model model) {
        List<CategoryDto> categories = categoryService.findWithTotalProductsMoreThan(quantity);
        model.addAttribute("categories", categories);
        return "category/categories";

    @GetMapping("/category-sales")
    public String getCategorySales(Model model) {
        List<CategorySalesDto> categorySalesList = categoryService.findTotalSalesByCategory();
        model.addAttribute("categorySales", categorySalesList);
        return "category/category-sales";

    }
}

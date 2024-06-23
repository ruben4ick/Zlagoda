package com.zlagoda.controller;

import com.zlagoda.dto.CategoryDto;
import com.zlagoda.dto.ProductDto;
import com.zlagoda.entity.Category;
import com.zlagoda.service.ProductService;
import com.zlagoda.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String products(Model model) {
        List<ProductDto> products = productService.getAll();
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "product/products";
    }

    @GetMapping("/category-search")
    public String productsByCategory(@RequestParam(value = "category_number", required = false, defaultValue = "-1" ) Long categoryNumber, Model model) {
        if (categoryNumber == -1) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/category-search";
        }
        model.addAttribute("products", productService.findByCategory(categoryNumber));
        return "product/products";
    }

    @GetMapping("/name-search")
    public String searchProductsByName(@RequestParam(value = "name", required = false, defaultValue = "null") String name, Model model) {
        if (name.equals("null"))
            return "product/name-search";
        model.addAttribute("products", productService.findByName(name));
        return "product/products";
    }

    @GetMapping("/add")
    public String productAdd(Model model) {
        List<CategoryDto> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductDto());
        return "product/products-add";
    }

    @PostMapping("/add")
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryDto> categories = categoryService.getAll();
            model.addAttribute("categories", categories);
            model.addAttribute("product", productDto);
            return "product/products-add";
        }
        productService.create(productDto);
        return "redirect:/products";
    }

    @GetMapping("/edit/{productId}")
    public String editProduct(@PathVariable("productId") Long productId, Model model) {
        Optional<ProductDto> productOpt = productService.getById(productId);
        if (productOpt.isPresent()) {
            List<CategoryDto> categories = categoryService.getAll();
            model.addAttribute("product", productOpt.get());
            model.addAttribute("categories", categories);
            return "product/products-edit";
        } else {
            return "redirect:/products";
        }
    }

    @PostMapping("/edit/{productId}")
    public String editProduct(@PathVariable("productId") Long productId, @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryDto> categories = categoryService.getAll();
            model.addAttribute("product", product);
            model.addAttribute("categories", categories);
            return "product/products-edit";
        }
        product.setId(productId);
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        productService.delete(productId);
        return "redirect:/products";
    }

    @GetMapping("/neverSold")
    public String findNotInStoreNeverSoldProducts(Model model) {
        List<ProductDto> products = productService.findNotInStoreNeverSoldProducts();
        model.addAttribute("products", products);
        return "/product/products";
    }
}

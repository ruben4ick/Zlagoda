package com.zlagoda.controller;

import com.zlagoda.dto.ProductDto;
import com.zlagoda.dto.StoreProductDto;
import com.zlagoda.service.StoreProductService;
import com.zlagoda.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store-products")
public class StoreProductController {

    private final StoreProductService storeProductService;
    private final ProductService productService;

    @GetMapping
    public String listStoreProducts(Model model) {
        List<StoreProductDto> storeProducts = storeProductService.getAll();
        model.addAttribute("storeProducts", storeProducts);
        return "store-product/store-products";
    }

    @GetMapping("/promoted")
    public String listStoreProductsPromoted(Model model){
        List<StoreProductDto> storeProducts = storeProductService.getPromotionalProducts();
        model.addAttribute("storeProducts", storeProducts);
        return "store-product/store-products-promoted";
    }

    @GetMapping("/standard")
    public String listStoreProductsStandard(Model model){
        List<StoreProductDto> storeProducts = storeProductService.getStandardProducts();
        model.addAttribute("storeProducts", storeProducts);
        return "store-product/store-products-standard";
    }

    @GetMapping("/add")
    public String storeProductAdd(Model model) {
        List<ProductDto> products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("storeProduct", new StoreProductDto());
        return "store-product/store-products-add";
    }


    @PostMapping("/add")
    public String saveStoreProduct(@Valid @ModelAttribute("storeProduct") StoreProductDto storeProductDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ProductDto> products = productService.getAll();
            model.addAttribute("products", products);
            model.addAttribute("storeProduct", storeProductDto);
            return "store-product/store-products-add";
        }
        storeProductService.create(storeProductDto);
        return "redirect:/store-products";
    }

    @GetMapping("/edit/{upc}")
    public String editStoreProduct(@PathVariable("upc") String upc, Model model) {
        Optional<StoreProductDto> storeProductOpt = storeProductService.getById(upc);

        if (storeProductOpt.isPresent()) {
            model.addAttribute("storeProduct", storeProductOpt.get());
            return "store-product/store-products-edit";
        } else {
            return "redirect:/store-products";
        }
    }

    @PostMapping("/edit/{upc}")
    public String editStoreProduct(@PathVariable("upc") String upc, @ModelAttribute("storeProduct") StoreProductDto storeProduct, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storeProduct", storeProduct);
            return "store-product/store-products-edit";
        }
        storeProduct.setUpc(upc);
        storeProductService.update(storeProduct);
        return "redirect:/store-products";
    }


    @GetMapping("/delete/{upc}")
    public String deleteStoreProduct(@PathVariable("upc") String upc) {
        storeProductService.delete(upc);
        return "redirect:/store-products";
    }

    // 14
    @GetMapping("/{upc}")
    public String findStoreProductByUpc(@PathVariable("upc") String upc, Model model) {
        Optional<StoreProductDto> storeProduct = storeProductService.getById(upc);
        if (storeProduct.isPresent()) {
            model.addAttribute("storeProduct", storeProduct.get());
        }
        return "store-product/store-products-edit";
    }

    @GetMapping("/add/prom/{upc}")
    public String makeProm(@PathVariable String upc, HttpServletRequest request) {
        storeProductService.addPromotionStoreProduct(upc);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/remove/prom/{upc}")
    public String unmakeProm(@PathVariable String upc) {
        storeProductService.removePromotionStoreProduct(upc);
        return "redirect:/store-products";
    }

    @GetMapping("/upc")
    public String getStoreProductByUPC(@RequestParam(value = "upc_search", required = false, defaultValue = "null")
                                       String upc_search, Model model){
        if (upc_search.equals("null"))
            return "store-product/upc_search";

        Optional<StoreProductDto> storeProductOpt = storeProductService.getById(upc_search);
        if (storeProductOpt.isPresent()) {
            StoreProductDto storeProduct = storeProductOpt.get();
            model.addAttribute("storeProducts", List.of(storeProduct));
        } else {
            model.addAttribute("storeProducts", List.of());
        }
        return "store-product/store-products";
    }

}

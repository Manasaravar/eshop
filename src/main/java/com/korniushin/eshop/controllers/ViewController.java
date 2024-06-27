package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.BrandService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Brand;
import com.korniushin.eshop.model.entities.Category;
import com.korniushin.eshop.model.dao.interfaces.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final CategoryService categoryService;
    private final BrandService brandService;



    @GetMapping("/index")

    public String getIndexPage(Model model){

        List <Brand> brands = brandService.all().stream()
                .map(brand -> Brand.builder()
                        .title(brand.getTitle())
                        .build())
                .toList();

        model.addAttribute("brands", brands);


        List <Category> categories = categoryService.all().stream()
                        .map(category -> Category.builder()
                                .title(category.getTitle())
                                .build())
                                .toList();
        model.addAttribute("categories", categories);


        return "index";
    }


}

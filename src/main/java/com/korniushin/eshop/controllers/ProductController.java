package com.korniushin.eshop.controllers;



import com.korniushin.eshop.DTO.ProductDTO;
import com.korniushin.eshop.model.dao.interfaces.*;
import com.korniushin.eshop.model.entities.*;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final OrderService orderService;
    private final UserService userService;



    @GetMapping
    public String getProducts(Model model, @Param("keyword") String keyword) {

        List<Product> products = productService.all();

        model.addAttribute("products", products);
        model.addAttribute("categories", getCategories());
        model.addAttribute("brands", getBrands());


        //поиск

        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
        // поиск

        return "products";
    }
//
    @GetMapping("/{id}")
    public String ProductDetailsView(@PathVariable Long id, Model model){

        model.addAttribute("categories", getCategories());
        model.addAttribute("brands", getBrands());

        final Product product= productService.findById(id).get();

        ProductDTO productDTO = ProductDTO.builder()
                        .id(product.getId())
                        .price(product.getPrice())
                        .article(product.getArticle())
                        .color(product.getColor())
                        .unit(product.getUnit())
                        .brand(product.getBrand().getTitle())
                        .category(product.getCategory().getTitle())
                        .composition(product.getComposition().getTitle())
                        .build();

        model.addAttribute("product", productDTO);

        return "productdetails";
    }
//
//    @PostMapping("/addPosition")
//    public String addPosition(@PathVariable Long productId, Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Order order = orderService.findOrderByUserId(userService.findById(user.getId()).get().getId());
//
//        final Product product = productService.findById(productId).get();
//       // if (product.getQuantity() >= quantity) {
//            orderService.addPosition(order, product, 1);
//      //  }
//        model.addAttribute("order", order);
//        return "cart";
//    }

    public List<Brand> getBrands () {
        List<Brand> brands = brandService.all().stream()
                .map(brand -> Brand.builder()
                        .title(brand.getTitle())
                        .build())
                .toList();
        return brands;
    }

        public List<Category> getCategories() {

            List <Category> categories = categoryService.all().stream()
                .map(category -> Category.builder()
                        .title(category.getTitle())
                        .build())
                .toList();
            return categories;
    }
}

package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.Product;
import com.korniushin.eshop.model.entities.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductService userService;


    @GetMapping
    public String cart(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderService.findOrderByUserId(userService.findById(user.getId()).get().getId());
        model.addAttribute("order", order);
        return "cart";
    }

    @PostMapping("/paid/{id}")
    public String paid(@PathVariable Long id) {
        orderService.pay(id);
        return "redirect:/";
    }

//    @PostMapping("/addPosition")
//    public void addPosition(Long productId, Integer quantity, Long orderId) {
//        final Order order = orderService.findById(orderId).get();
//        final Product product = productService.findById(productId).get();
//        if (product.getQuantity() >= quantity) {
//            orderService.addPosition(order, product, quantity);
//        }
//    }

//    @PostMapping ("/delPosition")
//    public void deletePosition(Long productId, Integer quantity, Long orderId){
//        Order order = orderService.findById(orderId).get();
//        final Product product = productService.findById(productId).get();
//        if(order.getTotalQuantity() >= quantity) {
//            orderService.delPosition(order, product, quantity);
//        }

//    }
}

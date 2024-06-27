package com.korniushin.eshop.controllers;

import ch.qos.logback.core.model.Model;
import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.Product;
import com.korniushin.eshop.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartUpdateController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductService userService;

    @PostMapping("/add")
    public void add (@RequestParam Long id, @RequestParam Integer quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderService.findOrderByUserId(userService.findById(user.getId()).get().getId());
       // final Order order = orderService.findById(orderId).get();
        Product product = productService.findById(id).get();
       // if (product.getQuantity() >= quantity) {
            orderService.addPosition(order, product, quantity);
       // }
    //   System.out.println("Получен запрос " + id);
    }
}

package com.korniushin.eshop.DTO;

import com.korniushin.eshop.model.entities.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private Integer price;
    private String color;
    private String article;
    private Integer quantity;
    private String unit;
    private String brand;
    private String category;
    private String composition;

}
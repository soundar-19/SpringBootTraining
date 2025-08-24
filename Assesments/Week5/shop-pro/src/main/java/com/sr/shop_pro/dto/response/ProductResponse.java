package com.sr.shop_pro.dto.response;

import java.math.BigDecimal;

import com.sr.shop_pro.domain.Product;
import com.sr.shop_pro.domain.Category;

import lombok.Data;
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;

    public static ProductResponse toDTO(Product product){
        ProductResponse dto = new ProductResponse();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.description = product.getDescription();
        dto.price = product.getPrice();
        dto.category = product.getCategory();
        return dto;
    }
}
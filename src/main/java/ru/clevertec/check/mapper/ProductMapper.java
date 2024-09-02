package ru.clevertec.check.mapper;

import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.ProductDto;
import ru.clevertec.check.model.Product;

import java.text.DecimalFormat;

@Component
public class ProductMapper {
    public Product toProduct(ProductDto dto) {
        return Product.builder()
                .description(dto.getDescription())
                .price(dto.getPrice())
                .isWholesaleProduct(dto.getIsPromotional())
                .build();
    }
}

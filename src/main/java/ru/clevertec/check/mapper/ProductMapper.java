package ru.clevertec.check.mapper;

import by.st.cash_receipt.dto.ProductDto;
import by.st.cash_receipt.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductDto dto) {
        return Product.builder()
                .description(dto.getDescription())
                .price(dto.getPrice())
                .isPromotional(dto.getIsPromotional())
                .build();
    }
}

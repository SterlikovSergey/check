package ru.clevertec.check.mapper;

import org.springframework.stereotype.Component;

@Component
public class DiscountCardMapper {
    public DiscountCard toDiscountCard(DiscountCardDto dto) {
        return DiscountCard.builder()
                .discountRate(dto.getDiscountRate())
                .build();
    }
}

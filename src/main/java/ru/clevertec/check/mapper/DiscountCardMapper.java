package ru.clevertec.check.mapper;

import org.springframework.stereotype.Component;
import ru.clevertec.check.dto.DiscountCardDto;
import ru.clevertec.check.model.DiscountCard;

@Component
public class DiscountCardMapper {
    public DiscountCard toDiscountCard(DiscountCardDto dto) {
        return DiscountCard.builder()
                .discountAmount(dto.getDiscountRate())
                .number(dto.getCardNumber())
                .build();
    }
}

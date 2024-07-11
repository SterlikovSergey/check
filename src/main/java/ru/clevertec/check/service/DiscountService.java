package ru.clevertec.check.service;

import org.springframework.stereotype.Service;
import ru.clevertec.check.repository.Discount;
import ru.clevertec.check.model.DiscountCard;
@Service
public class DiscountService {
    private final Discount discount;

    public DiscountService(Discount discount) {
        this.discount = discount;
    }

    public DiscountCard getDiscountCard(Integer number) {
        return discount.getDiscountCardByNumber(number);
    }

    public DiscountCard addDiscountCard(DiscountCard discountCard) {
        return discount.save(discountCard);
    }

    public DiscountCard getDiscountCardBy(Long id) {
        return discount.findById(id).orElseThrow();
    }

}

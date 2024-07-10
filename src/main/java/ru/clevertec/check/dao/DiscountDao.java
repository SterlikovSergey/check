package ru.clevertec.check.dao;

import ru.clevertec.check.model.DiscountCard;

public interface DiscountDao {
    DiscountCard getDiscountCardByNumber(Integer discountCard);
}

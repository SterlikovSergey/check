package ru.clevertec.check.service;

import ru.clevertec.check.dao.DiscountDao;
import ru.clevertec.check.model.DiscountCard;

public class DiscountService {
    private final DiscountDao discountDao;

    public DiscountService(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    public DiscountCard getDiscountCard(Integer number) {
        return discountDao.getDiscountCardByNumber(number);
    }

}

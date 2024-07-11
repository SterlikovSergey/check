package ru.clevertec.check.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.model.DiscountCard;
@Repository
public interface Discount extends CrudRepository<DiscountCard, Long> {
    DiscountCard getDiscountCardByNumber(Integer discountCard);
}

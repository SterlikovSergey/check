package ru.clevertec.check.factory;

import org.springframework.stereotype.Service;
import ru.clevertec.check.calculater.PriceCalculator;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.ReceiptItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service
public class CashReceiptFactory {
    public static Check createCheck(List<ReceiptItem> receiptItems, DiscountCard discountCard) {
        List<ReceiptItem> updatedReceiptItems = PriceCalculator.calculateTotalDiscount(receiptItems, discountCard);

        BigDecimal totalPrice = updatedReceiptItems.stream()
                .map(ReceiptItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount = updatedReceiptItems.stream()
                .map(ReceiptItem::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalWithDiscount = totalPrice.subtract(totalDiscount);


        return  Check.builder()
                .checkDate(LocalDate.now())
                .checkTime(LocalTime.now())
                .receiptItems(updatedReceiptItems)
                .discountCard(discountCard != null ? discountCard.getNumber() : null)
                .discountPercentage(discountCard != null ? discountCard.getDiscountAmount() : null)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .totalWithDiscount(totalWithDiscount)
                .build();
    }
}

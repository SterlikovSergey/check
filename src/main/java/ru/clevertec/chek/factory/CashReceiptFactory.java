package ru.clevertec.chek.factory;

import ru.clevertec.chek.calculater.PriceCalculator;
import ru.clevertec.chek.model.Check;
import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.ReceiptItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CashReceiptFactory {
    public static Check createCheck(List<ReceiptItem> receiptItems, DiscountCard discountCard, Long balanceDebitCard) {

        List<ReceiptItem> updatedReceiptItems = PriceCalculator.calculateTotalDiscount(receiptItems, discountCard);

        BigDecimal totalPrice = updatedReceiptItems.stream()
                .map(ReceiptItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDiscount = updatedReceiptItems.stream()
                .map(ReceiptItem::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithDiscount = totalPrice.subtract(totalDiscount);

        return new Check.CheckBuilder()
                .setCheckDate(LocalDate.now())
                .setCheckTime(LocalTime.now())
                .setReceiptItems(updatedReceiptItems)
                .setDiscountCard(discountCard != null ? discountCard.getNumber() : null)
                .setDiscountPercentage(discountCard != null ? discountCard.getDiscountAmount() : null)
                .setTotalPrice(totalPrice)
                .setTotalDiscount(totalDiscount)
                .setTotalWithDiscount(totalWithDiscount)
                .build();
    }
}

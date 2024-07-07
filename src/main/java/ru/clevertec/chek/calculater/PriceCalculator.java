package ru.clevertec.chek.calculater;

import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculator {

    public static List<ReceiptItem> calculateTotalDiscount(List<ReceiptItem> receiptItems, DiscountCard discountCard) {
        for (ReceiptItem item : receiptItems) {
            BigDecimal price = item.getProduct().getPrice();
            item.setPrice(price);

            BigDecimal total = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setTotal(total);

            BigDecimal discount = BigDecimal.ZERO;
            if (item.getProduct().getWholesaleProduct() && item.getQuantity() >= 5) {
                discount = total.multiply(BigDecimal.valueOf(0.10));
            } else if (!item.getProduct().getWholesaleProduct() && discountCard != null) {
                discount = total.multiply(BigDecimal.valueOf(discountCard.getDiscountAmount()).divide(BigDecimal.valueOf(100)));
            }
            item.setDiscount(discount);

            BigDecimal discountedTotal = total.subtract(discount);
            item.setTotal(discountedTotal);
        }
        return receiptItems;
    }
}

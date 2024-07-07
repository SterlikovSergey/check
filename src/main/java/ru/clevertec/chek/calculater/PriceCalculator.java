package ru.clevertec.chek.calculater;

import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculator {

    public static List<ReceiptItem> calculateTotalDiscount(List<ReceiptItem> receiptItems, DiscountCard discountCard) {
        for (ReceiptItem item : receiptItems) {
            // Установка цены из продукта
            BigDecimal price = item.getProduct().getPrice();
            item.setPrice(price);

            // Расчет общей стоимости
            BigDecimal total = price.multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setTotal(total);

            // Расчет скидки
            BigDecimal discount = BigDecimal.ZERO;
            if (item.getProduct().getWholesaleProduct() && item.getQuantity() >= 5) {
                // Скидка 10% на оптовый товар
                discount = total.multiply(BigDecimal.valueOf(0.10));
            } else if (!item.getProduct().getWholesaleProduct() && discountCard != null) {
                // Скидка по карте, если товар не оптовый
                discount = total.multiply(BigDecimal.valueOf(discountCard.getDiscountAmount()).divide(BigDecimal.valueOf(100)));
            }
            item.setDiscount(discount);

            // Обновление общей стоимости с учетом скидки
            BigDecimal discountedTotal = total.subtract(discount);
            item.setTotal(discountedTotal);
        }
        return receiptItems;
    }
}

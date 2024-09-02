package ru.clevertec.check;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.factory.CashReceiptFactory;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.model.ReceiptItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashReceiptFactoryTest {
    @Test
    public void test_create_check_with_valid_receipt_items_and_discount_card() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", BigDecimal.valueOf(100),10, false);
        Product product2 = new Product(2L, "Product 2", BigDecimal.valueOf(200),20, true);

        ReceiptItem item1 = new ReceiptItem(1L, product1, 2.0, BigDecimal.valueOf(100), BigDecimal.valueOf(200), BigDecimal.ZERO);
        ReceiptItem item2 = new ReceiptItem(2L, product2, 5.0, BigDecimal.valueOf(200), BigDecimal.valueOf(1000), BigDecimal.ZERO);

        List<ReceiptItem> receiptItems = List.of(item1, item2);
        DiscountCard discountCard = new DiscountCard(1L, 12345, 10);

        // Act
        Check check = CashReceiptFactory.createCheck(receiptItems, discountCard);

        // Assert
        assertNotNull(check);
        assertEquals(LocalDate.now(), check.getCheckDate());
        assertEquals(LocalTime.now().getHour(), check.getCheckTime().getHour());
        assertEquals(LocalTime.now().getMinute(), check.getCheckTime().getMinute());
        assertEquals(receiptItems.size(), check.getReceiptItems().size());
        assertEquals(discountCard.getNumber(), check.getDiscountCard());
        assertEquals(discountCard.getDiscountAmount(), check.getDiscountPercentage());
        assertEquals(BigDecimal.valueOf(1200).setScale(2), check.getTotalPrice().setScale(2));
        assertEquals(BigDecimal.valueOf(100).setScale(2), check.getTotalDiscount().setScale(2));
        assertEquals(BigDecimal.valueOf(1100).setScale(2), check.getTotalWithDiscount().setScale(2));
    }
}

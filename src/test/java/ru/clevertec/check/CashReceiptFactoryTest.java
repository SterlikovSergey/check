package ru.clevertec.check;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.factory.CashReceiptFactory;
import ru.clevertec.chek.model.Check;
import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.Product;
import ru.clevertec.chek.model.ReceiptItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CashReceiptFactoryTest {
    @Test
    public void test_create_check_with_valid_receipt_items_and_discount_card() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", BigDecimal.valueOf(10.00),10, false);
        Product product2 = new Product(2L, "Product 2", BigDecimal.valueOf(20.00),10, true);
        ReceiptItem item1 = new ReceiptItem(product1, 2.0, BigDecimal.valueOf(10.00));
        ReceiptItem item2 = new ReceiptItem(product2, 5.0, BigDecimal.valueOf(20.00));
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
        assertTrue(check.getTotalPrice().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(check.getTotalDiscount().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(check.getTotalWithDiscount().compareTo(BigDecimal.ZERO) > 0);
    }
}

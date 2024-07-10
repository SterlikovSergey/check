package ru.clevertec.check.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Check {
    private LocalDate checkDate;
    private LocalTime checkTime;
    private List<ReceiptItem> receiptItems;
    private Integer discountCard;
    private Integer discountPercentage;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalWithDiscount;

    private Check() {
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public LocalTime getCheckTime() {
        return checkTime;
    }

    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public Integer getDiscountCard() {
        return discountCard;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public static class CheckBuilder {
        private Check check;

        public CheckBuilder() {
            check = new Check();
        }

        public CheckBuilder setCheckDate(LocalDate checkDate) {
            check.checkDate = checkDate;
            return this;
        }

        public CheckBuilder setCheckTime(LocalTime checkTime) {
            check.checkTime = checkTime;
            return this;
        }

        public CheckBuilder setReceiptItems(List<ReceiptItem> receiptItems) {
            check.receiptItems = receiptItems;
            return this;
        }

        public CheckBuilder setDiscountCard(Integer discountCard) {
            check.discountCard = discountCard;
            return this;
        }

        public CheckBuilder setDiscountPercentage(Integer discountPercentage) {
            check.discountPercentage = discountPercentage;
            return this;
        }

        public CheckBuilder setTotalPrice(BigDecimal totalPrice) {
            check.totalPrice = totalPrice;
            return this;
        }

        public CheckBuilder setTotalDiscount(BigDecimal totalDiscount) {
            check.totalDiscount = totalDiscount;
            return this;
        }

        public CheckBuilder setTotalWithDiscount(BigDecimal totalWithDiscount) {
            check.totalWithDiscount = totalWithDiscount;
            return this;
        }

        public Check build() {
            return check;
        }
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkDate=" + checkDate +
                ", checkTime=" + checkTime +
                ", receiptItems=" + receiptItems +
                ", discountCard=" + discountCard +
                ", discountPercentage=" + discountPercentage +
                ", totalPrice=" + totalPrice +
                ", totalDiscount=" + totalDiscount +
                ", totalWithDiscount=" + totalWithDiscount +
                '}';
    }
}

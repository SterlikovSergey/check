package ru.clevertec.chek.model;

import java.math.BigDecimal;

public class ReceiptItem {
    private Product product;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal total;
    private BigDecimal discount;

    public ReceiptItem(Product product, Double quantity, BigDecimal price, BigDecimal total, BigDecimal discount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.discount = discount;
    }

    public ReceiptItem(Product product, Double quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}

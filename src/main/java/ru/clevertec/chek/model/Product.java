package ru.clevertec.chek.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private Boolean isWholesaleProduct;

    public Product() {
    }

    public Product(Long id, String description, BigDecimal price, Integer quantityInStock, Boolean isWholesaleProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.isWholesaleProduct = isWholesaleProduct;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public Boolean getWholesaleProduct() {
        return isWholesaleProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", isWholesaleProduct=" + isWholesaleProduct +
                '}';
    }
}

package ru.clevertec.check.model;

public class DiscountCard {
    private Long id;
    private Integer number;
    private Integer discountAmount;

    public DiscountCard() {
    }

    public DiscountCard(Long id, Integer number, Integer discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", discountAmount=" + discountAmount +
                '}';
    }
}

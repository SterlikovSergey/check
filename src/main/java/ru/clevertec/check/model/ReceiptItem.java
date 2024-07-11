package ru.clevertec.check.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Data
public class ReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal total;
    private BigDecimal discount;

}

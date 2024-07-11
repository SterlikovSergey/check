package ru.clevertec.check.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkDate;
    private LocalTime checkTime;

    @OneToMany
    private List<ReceiptItem> receiptItems;

    private Integer discountCard;
    private Integer discountPercentage;
    private BigDecimal totalPrice;
    private BigDecimal totalDiscount;
    private BigDecimal totalWithDiscount;


}

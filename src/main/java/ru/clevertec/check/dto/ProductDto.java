package ru.clevertec.check.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
public class ProductDto {
    private String description;
    private BigDecimal price;
    private Boolean isPromotional;
}

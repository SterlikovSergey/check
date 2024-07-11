package ru.clevertec.check.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ProductDto {
    private String description;
    private Double price;
    private Boolean isPromotional;
}

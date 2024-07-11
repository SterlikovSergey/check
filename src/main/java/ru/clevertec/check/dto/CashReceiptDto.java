package ru.clevertec.check.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Data
@Getter
@Setter
@ToString
public class CashReceiptDto {
    private Map<Long,Long> map;
}

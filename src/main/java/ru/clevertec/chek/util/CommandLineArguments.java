package ru.clevertec.chek.util;

import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {
    private final Map<Long, Double> idsQuantitysMap;
    private Integer discountCardNumber;
    private Long balanceDebitCard;

    public CommandLineArguments(String[] args) {
        idsQuantitysMap = new HashMap<>();
        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                String[] split = arg.split("-");
                Long id = Long.parseLong(split[0]);
                Double quantity = Double.parseDouble(split[1]);
                idsQuantitysMap.merge(id, quantity, Double::sum);
            } else if (arg.startsWith("discountCard=")) {
                discountCardNumber = Integer.parseInt(arg.substring(13));
            } else if (arg.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Long.parseLong(arg.substring(17));
            }
        }
    }

    public Map<Long, Double> getIdsQuantitysMap() {
        return idsQuantitysMap;
    }

    public Integer getDiscountCardNumber() {
        return discountCardNumber;
    }

    public Long getBalanceDebitCard() {
        return balanceDebitCard;
    }

}

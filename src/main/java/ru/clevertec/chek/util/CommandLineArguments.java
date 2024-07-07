package ru.clevertec.chek.util;

import ru.clevertec.chek.exeption.Error;
import ru.clevertec.chek.writer.Writer;
import ru.clevertec.chek.writer.impl.ErrorWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {
    private final Map<Long, Double> idsQuantitysMap;
    private Integer discountCardNumber;
    private Long balanceDebitCard;

    public CommandLineArguments(String[] args) throws IOException {
        idsQuantitysMap = new HashMap<>();
        boolean hasProducts = false;
        boolean hasBalanceDebitCard = false;

        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                hasProducts = true;
                String[] split = arg.split("-");
                Long id = Long.parseLong(split[0]);
                Double quantity = Double.parseDouble(split[1]);
                idsQuantitysMap.merge(id, quantity, Double::sum);
            } else if (arg.startsWith("discountCard=")) {
                discountCardNumber = Integer.parseInt(arg.substring(13));
            } else if (arg.startsWith("balanceDebitCard=")) {
                hasBalanceDebitCard = true;
                balanceDebitCard = Long.parseLong(arg.substring(17));
            }
        }

        try {
            if (!hasProducts) {
                throw new Error("ERROR","BAD REQUEST: there are no products in the request");
            }

            if (!hasBalanceDebitCard) {
                throw new Error("ERROR","BAD REQUEST: there is no payment card in the request");
            }
        } catch (Error e) {
            Writer<Error> writer = new ErrorWriter();
            writer.write(new Error(e.getMessage(),e.getDescription()));
            throw new IllegalArgumentException();
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

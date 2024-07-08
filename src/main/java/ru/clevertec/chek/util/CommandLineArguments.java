package ru.clevertec.chek.util;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.writer.Writer;
import ru.clevertec.chek.writer.impl.ErrorWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {
    private final Map<Long, Double> idsQuantitysMap;
    private Integer discountCardNumber;
    private BigDecimal balanceDebitCard;

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
                balanceDebitCard = new BigDecimal(arg.substring(17));
            }
        }

        try {
            if (!hasProducts) {
                throw new CustomError("ERROR", "BAD REQUEST: there are no products in the request");
            }

            if (!hasBalanceDebitCard) {
                throw new CustomError("ERROR", "BAD REQUEST: there is no payment card in the request");
            }
        } catch (CustomError e) {
            Writer<CustomError> writer = new ErrorWriter();
            writer.write(new CustomError(e.getMessage(), e.getDescription()));
            System.exit(1);
        }
    }

    public Map<Long, Double> getIdsQuantitysMap() {
        return idsQuantitysMap;
    }

    public Integer getDiscountCardNumber() {
        return discountCardNumber;
    }

    public BigDecimal getBalanceDebitCard() {
        return balanceDebitCard;
    }
}

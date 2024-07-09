package ru.clevertec.chek.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {
    public static final String DISCOUNT_CARD_PREFIX = "discountCard=";
    public static final String BALANCE_DEBIT_CARD_PREFIX = "balanceDebitCard=";
    public static final String PATH_TO_FILE_PREFIX = "pathToFile=";
    public static final String SAVE_TO_FILE_PREFIX = "saveToFile=";
    private final Map<Long, Double> idsQuantitysMap;
    private final Integer discountCardNumber;
    private final BigDecimal balanceDebitCard;
    private final String pathToFile;
    private final String saveToFile;

    private CommandLineArguments(Builder builder) {
        this.idsQuantitysMap = builder.idsQuantitysMap;
        this.discountCardNumber = builder.discountCardNumber;
        this.balanceDebitCard = builder.balanceDebitCard;
        this.pathToFile = builder.pathToFile;
        this.saveToFile = builder.saveToFile;
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

    public String getPathToFile() {
        return pathToFile;
    }

    public String getSaveToFile() {
        return saveToFile;
    }

    public static class Builder {
        private final Map<Long, Double> idsQuantitysMap = new HashMap<>();
        private Integer discountCardNumber;
        private BigDecimal balanceDebitCard;
        private String pathToFile;
        private String saveToFile;

        public void withProduct(Long id, Double quantity) {
            idsQuantitysMap.merge(id, quantity, Double::sum);
        }

        public void withDiscountCardNumber(String discountCard) {
            this.discountCardNumber = Integer.parseInt(discountCard.replace(DISCOUNT_CARD_PREFIX, ""));
        }

        public void withBalanceDebitCard(String balanceDebitCard) {
            if (balanceDebitCard != null && balanceDebitCard.startsWith(CommandLineArguments.BALANCE_DEBIT_CARD_PREFIX)) {
                String numericPart = balanceDebitCard.substring(CommandLineArguments.BALANCE_DEBIT_CARD_PREFIX.length());
                this.balanceDebitCard = new BigDecimal(numericPart);
            }
        }

        public void withPathToFile(String pathToFile) {
            this.pathToFile = pathToFile.replace(PATH_TO_FILE_PREFIX, "");
        }

        public void withSaveToFile(String saveToFile) {
            this.saveToFile = saveToFile.replace(SAVE_TO_FILE_PREFIX, "");
        }

        public CommandLineArguments build() {
            return new CommandLineArguments(this);
        }
    }
}

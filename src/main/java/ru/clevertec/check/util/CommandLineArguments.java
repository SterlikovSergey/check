package ru.clevertec.check.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CommandLineArguments {

    public static final String DISCOUNT_CARD_PREFIX = "discountCard=";
    public static final String BALANCE_DEBIT_CARD_PREFIX = "balanceDebitCard=";
    public static final String PATH_TO_FILE_PREFIX = "pathToFile=";
    public static final String SAVE_TO_FILE_PREFIX = "saveToFile=";
    public static final String DATASOURCE_URL = "datasource.url=";
    public static final String DATASOURCE_USERNAME = "datasource.username=";
    public static final String DATASOURCE_PASSWORD = "datasource.password=";

    private final Map<Long, Double> idsQuantitysMap;
    private final Integer discountCardNumber;
    private final BigDecimal balanceDebitCard;
    private final String pathToFile;
    private final String saveToFile;
    private final String datasourceUrl;
    private final String datasourceUsername;
    private final String datasourcePassword;

    private CommandLineArguments(Builder builder) {
        this.idsQuantitysMap = builder.idsQuantitysMap;
        this.discountCardNumber = builder.discountCardNumber;
        this.balanceDebitCard = builder.balanceDebitCard;
        this.pathToFile = builder.pathToFile;
        this.saveToFile = builder.saveToFile;
        this.datasourceUrl = builder.datasourceUrl;
        this.datasourceUsername = builder.datasourceUsername;
        this.datasourcePassword = builder.datasourcePassword;
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

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public static class Builder {
        private final Map<Long, Double> idsQuantitysMap = new HashMap<>();
        private Integer discountCardNumber;
        private BigDecimal balanceDebitCard;
        private String pathToFile;
        private String saveToFile;
        private String datasourceUrl;
        private String datasourceUsername;
        private String datasourcePassword;

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

        public void withDatasourceUrl(String datasourceUrl) {
            this.datasourceUrl = datasourceUrl.replace(DATASOURCE_URL, "");
        }

        public void withDatasourceUsername(String datasourceUsername) {
            this.datasourceUsername = datasourceUsername.replace(DATASOURCE_USERNAME, "");
        }

        public void withDatasourcePassword(String datasourcePassword) {
            this.datasourcePassword = datasourcePassword.replace(DATASOURCE_PASSWORD, "");
        }

        public CommandLineArguments build() {
            return new CommandLineArguments(this);
        }
    }
}

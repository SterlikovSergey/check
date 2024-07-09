package ru.clevertec.chek.util;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.exeption.CustomErrorFactory;

public class CommandLineParser {
    public CommandLineArguments parse(String[] args) throws CustomError {
        CommandLineArguments.Builder builder = new CommandLineArguments.Builder();
        boolean hasProducts = false;
        boolean hasBalanceDebitCard = false;
        boolean hasPathToFile = false;
        boolean hasSaveToFile = false;
        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                hasProducts = true;
                String[] split = arg.split("-");
                Long id = Long.parseLong(split[0]);
                Double quantity = Double.parseDouble(split[1]);
                builder.withProduct(id, quantity);
            } else if (arg.startsWith(CommandLineArguments.DISCOUNT_CARD_PREFIX)) {
                builder.withDiscountCardNumber(arg.substring(CommandLineArguments.DISCOUNT_CARD_PREFIX.length()));

            } else if (arg.startsWith(CommandLineArguments.BALANCE_DEBIT_CARD_PREFIX)) {
                hasBalanceDebitCard = true;
                builder.withBalanceDebitCard(arg);

            } else if (arg.startsWith(CommandLineArguments.PATH_TO_FILE_PREFIX)) {
                hasPathToFile = true;
                builder.withPathToFile(arg.substring(CommandLineArguments.PATH_TO_FILE_PREFIX.length()));
            } else if (arg.startsWith(CommandLineArguments.SAVE_TO_FILE_PREFIX)) {
                hasSaveToFile = true;
                builder.withSaveToFile(arg.substring(CommandLineArguments.SAVE_TO_FILE_PREFIX.length()));
            }
        }
        if (!hasProducts) {
            throw CustomErrorFactory.create("ERROR", "BAD REQUEST: there are no products in the request");
        }
        if (!hasBalanceDebitCard) {
            throw CustomErrorFactory.create("ERROR", "BAD REQUEST: there is no payment card in the request");
        }
        if (!hasPathToFile) {
            throw CustomErrorFactory.create("ERROR", "BAD REQUEST: there is no path to the request");
        }
        if (!hasSaveToFile) {
            throw CustomErrorFactory.create("ERROR", "BAD REQUEST: there is no save to the request");
        }
        return builder.build();
    }
}
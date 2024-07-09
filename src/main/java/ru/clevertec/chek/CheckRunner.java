package ru.clevertec.chek;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.exeption.CustomErrorFactory;
import ru.clevertec.chek.exeption.ErrorManager;
import ru.clevertec.chek.factory.CashReceiptFactory;
import ru.clevertec.chek.model.Check;
import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.ReceiptItem;
import ru.clevertec.chek.reader.impl.DiscountReader;
import ru.clevertec.chek.reader.impl.ProductReader;
import ru.clevertec.chek.service.DiscountService;
import ru.clevertec.chek.service.ProductService;
import ru.clevertec.chek.util.CommandLineArguments;
import ru.clevertec.chek.util.CommandLineParser;
import ru.clevertec.chek.writer.Writer;
import ru.clevertec.chek.writer.impl.CheckWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        DiscountService discountService = new DiscountService(new DiscountReader());
        CommandLineParser parser = new CommandLineParser();
        ErrorManager errorManager = new ErrorManager();
        try {
            CommandLineArguments arguments = parser.parse(args);
            ProductService productService = new ProductService(new ProductReader(arguments.getPathToFile()));
            List<ReceiptItem> receiptItems = productService.createListProductCheck(arguments.getIdsQuantitysMap());
            DiscountCard discountCard = discountService.getDiscountCardByNumber(arguments.getDiscountCardNumber());
            BigDecimal balanceDebitCard = arguments.getBalanceDebitCard();
            if (balanceDebitCard != null) {
                Check check = CashReceiptFactory.createCheck(receiptItems, discountCard);
                BigDecimal totalWithDiscount = check.getTotalWithDiscount();
                if (balanceDebitCard.compareTo(totalWithDiscount) < 0) {
                    throw CustomErrorFactory.create("ERROR", "NOT ENOUGH MONEY: The balance is less than the total amount of the check.");
                }
                Writer<Check> checkWriter = new CheckWriter(arguments.getSaveToFile());
                checkWriter.write(check);
            }
        } catch (CustomError customError) {
            String errorFile;
            if (customError.getMessage().contains("there is no path to the request")) {
                errorFile = "error_result.csv";
            } else {
                errorFile = "result.csv";
            }
            errorManager.handleError(customError, errorFile);
        }
    }
}

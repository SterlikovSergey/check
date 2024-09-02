package ru.clevertec.check;

import ru.clevertec.check.repository.impl.DiscountDaoImpl;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.service.DiscountService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.repository.Discount;
import ru.clevertec.check.repository.impl.ProductDaoImpl;
import ru.clevertec.check.database.DatabaseConnection;
import ru.clevertec.check.database.DatabaseInitializer;
import ru.clevertec.check.exeption.CustomError;
import ru.clevertec.check.exeption.CustomErrorFactory;
import ru.clevertec.check.exeption.ErrorManager;
import ru.clevertec.check.factory.CashReceiptFactory;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.model.ReceiptItem;
import ru.clevertec.check.util.CommandLineArguments;
import ru.clevertec.check.util.CommandLineParser;
import ru.clevertec.check.util.SqlQueryLoader;
import ru.clevertec.check.writer.Writer;
import ru.clevertec.check.writer.impl.CheckWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        CommandLineParser parser = new CommandLineParser();
        ErrorManager errorManager = new ErrorManager();
        try {
            CommandLineArguments arguments = parser.parse(args);
           /* DatabaseConnection databaseConnection = new DatabaseConnection(arguments.getDatasourceUrl(), arguments.getDatasourceUsername(), arguments.getDatasourcePassword());
            DatabaseInitializer.initializeDatabase(databaseConnection.getConnection());*/
            /*SqlQueryLoader.loadProductsFromFileAndInsertToDb(arguments.getPathToFile(), databaseConnection.getConnection());
            SqlQueryLoader.loadDiscountFromFileAndInsertToDb(databaseConnection.getConnection());
            Discount discount = new DiscountDaoImpl(databaseConnection);*/
            DiscountService discountService = new DiscountService(discount);
            DiscountCard discountCard = discountService.getDiscountCard(arguments.getDiscountCardNumber());
            Product product = new ProductDaoImpl(databaseConnection);
            ProductService productService = new ProductService(product);
            List<ReceiptItem> receiptItems = productService.createListProductCheck(arguments.getIdsQuantitysMap());
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


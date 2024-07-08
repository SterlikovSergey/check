package ru.clevertec.chek;

import ru.clevertec.chek.factory.CashReceiptFactory;
import ru.clevertec.chek.model.Check;
import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.ReceiptItem;
import ru.clevertec.chek.reader.impl.DiscountReader;
import ru.clevertec.chek.reader.impl.ProductReader;
import ru.clevertec.chek.service.DiscountService;
import ru.clevertec.chek.service.ProductService;
import ru.clevertec.chek.util.CommandLineArguments;
import ru.clevertec.chek.writer.Writer;
import ru.clevertec.chek.writer.impl.CheckWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        CommandLineArguments cmdArgs = new CommandLineArguments(args);
        ProductService productService = new ProductService(new ProductReader());
        DiscountService discountService = new DiscountService(new DiscountReader());

        List<ReceiptItem> receiptItems = productService.createListProductCheck(cmdArgs.getIdsQuantitysMap());
        DiscountCard discountCard = discountService.getDiscountCardByNumber(cmdArgs.getDiscountCardNumber());
        BigDecimal balanceDebitCard = cmdArgs.getBalanceDebitCard();

        Check check = CashReceiptFactory.createCheck(receiptItems, discountCard, balanceDebitCard);
        Writer<Check> checkWriter = new CheckWriter();
        checkWriter.write(check);
    }
}
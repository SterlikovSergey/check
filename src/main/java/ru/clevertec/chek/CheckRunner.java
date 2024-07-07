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

import java.io.IOException;
import java.util.List;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        CommandLineArguments cmdArgs = new CommandLineArguments(args);
        ProductService productService = new ProductService(new ProductReader());
        DiscountService discountService = new DiscountService(new DiscountReader());

        List<ReceiptItem> receiptItems = productService.createListProductCheck(cmdArgs.getIdsQuantitysMap());
        DiscountCard discountCard = discountService.getDiscountCardByNumber(cmdArgs.getDiscountCardNumber());
        Long balanceDebitCard = cmdArgs.getBalanceDebitCard();

        Check check = CashReceiptFactory.createCheck(receiptItems,discountCard,balanceDebitCard);

        receiptItems.forEach(System.out::println);

        System.out.println(check);

    }
}
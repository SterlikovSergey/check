package ru.clevertec.chek;

import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.model.Product;
import ru.clevertec.chek.reader.impl.DiscountReader;
import ru.clevertec.chek.reader.impl.ProductReader;
import ru.clevertec.chek.service.DiscountService;
import ru.clevertec.chek.service.ProductService;
import ru.clevertec.chek.util.CommandLineArguments;

import java.io.IOException;
import java.util.Map;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        CommandLineArguments cmdArgs = new CommandLineArguments(args);
        ProductService productService = new ProductService(new ProductReader());
        DiscountService discountService = new DiscountService(new DiscountReader());

        Map<Product, Double> productCheck = productService.createProductCheck(cmdArgs.getIdsQuantitysMap());
        DiscountCard discountCard = discountService.getDiscountCardByNumber(cmdArgs.getDiscountCardNumber());
        Long balanceDebitCard = cmdArgs.getBalanceDebitCard();

    }
}
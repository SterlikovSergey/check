package ru.clevertec.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.check.exeption.CustomError;
import ru.clevertec.check.factory.CashReceiptFactory;
import ru.clevertec.check.model.Check;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.DiscountService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.util.CommandLineArguments;
import ru.clevertec.check.util.CommandLineParser;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cash_receipt")
public class CashReceiptController {
    private final ProductService productService;
    private final DiscountService discountCardService;
    private final CashReceiptFactory cashReceiptFactory;
    private final CommandLineParser commandLineParser;


    @GetMapping("/check")
    public ResponseEntity<Check> getCashReceipt(@RequestParam String request) throws CustomError, IOException {
        String[] argument = new String[]{request};
        CommandLineArguments commandLineArguments1 = commandLineParser.parse(argument);
        productService.createListProductCheck(commandLineArguments1.getIdsQuantitysMap());
        DiscountCard discountCard = discountCardService.getDiscountCard(commandLineArguments1.getDiscountCardNumber());
        Check check = CashReceiptFactory.createCheck(productService.createListProductCheck(commandLineArguments1.getIdsQuantitysMap()), discountCard);
        return ResponseEntity.ok(check);
    }
}

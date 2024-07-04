package ru.clevertec.chek.service;

import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.reader.Reader;

import java.io.IOException;

public class DiscountService {
    Reader<DiscountCard> discountCardReader;

    public DiscountService(Reader<DiscountCard> discountCardReader) {
        this.discountCardReader = discountCardReader;
    }

    public DiscountCard getDiscountCardByNumber(Integer number) throws IOException {
        return discountCardReader.readFromFile().stream()
                .filter(card -> card.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }
}

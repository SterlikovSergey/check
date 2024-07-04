package ru.clevertec.chek.reader.impl;

import ru.clevertec.chek.model.DiscountCard;
import ru.clevertec.chek.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscountReader implements Reader<DiscountCard> {
    @Override
    public List<DiscountCard> readFromFile() throws IOException {
        List<DiscountCard> discountCards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/discountCards.csv"))) {
            String line;
            boolean firstLine = true; // Добавим флаг для первой строки
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Long id = Long.parseLong(parts[0]);
                    Integer number = Integer.parseInt(parts[1]);
                    Integer discountAmount = Integer.parseInt(parts[2]);
                    DiscountCard discountCard = new DiscountCard(id, number, discountAmount);
                    discountCards.add(discountCard);
                }
            }
        }
        return discountCards;
    }
}

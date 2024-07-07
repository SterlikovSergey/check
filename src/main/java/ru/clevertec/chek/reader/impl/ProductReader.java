package ru.clevertec.chek.reader.impl;


import ru.clevertec.chek.model.Product;
import ru.clevertec.chek.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductReader implements Reader<Product> {
    @Override
    public List<Product> readFromFile() throws IOException {
        List<Product> products = new ArrayList<>();
        String PATH = "./src/main/resources/products.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            boolean firstLine = true; // Добавим флаг для первой строки
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    Long id = Long.parseLong(parts[0]);
                    String description = parts[1];
                    double doublePrice = Double.parseDouble(parts[2].replace(",", "."));
                    BigDecimal price = BigDecimal.valueOf(doublePrice);
                    Integer quantityInStock = Integer.parseInt(parts[3]);
                    Boolean isWholesaleProduct = parts[4].equals("+");
                    Product product = new Product(id, description, price, quantityInStock, isWholesaleProduct);
                    products.add(product);
                }
            }
        }
        return products;
    }
}

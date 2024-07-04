package ru.clevertec.chek.service;

import ru.clevertec.chek.model.Product;
import ru.clevertec.chek.reader.Reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Reader<Product> productReader;

    public ProductService(Reader<Product> productReader) {
        this.productReader = productReader;
    }

    public List<Product> getAllProducts() throws IOException {
        return productReader.readFromFile();
    }

    public Map<Product, Double> createProductCheck(Map<Long, Double> idsQuantitysMap) throws IOException {
        Map<Product, Double> productCheck = new HashMap<>();
        List<Product> products = getAllProducts();

        for (Map.Entry<Long, Double> entry : idsQuantitysMap.entrySet()) {
            Long id = entry.getKey();
            Double quantity = entry.getValue();
            products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .ifPresent(product -> productCheck.put(product, quantity));
        }
        return productCheck;
    }
}

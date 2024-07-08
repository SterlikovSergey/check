package ru.clevertec.chek.service;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.model.Product;
import ru.clevertec.chek.model.ReceiptItem;
import ru.clevertec.chek.reader.Reader;
import ru.clevertec.chek.writer.Writer;
import ru.clevertec.chek.writer.impl.ErrorWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Reader<Product> productReader;
    private final Writer<CustomError> errorWriter;


    public ProductService(Reader<Product> productReader) {
        this.productReader = productReader;
        this.errorWriter = new ErrorWriter();
    }

    public List<Product> getAllProducts() throws IOException {
        return productReader.readFromFile();
    }

    public List<ReceiptItem> createListProductCheck(Map<Long, Double> idsQuantitysMap) throws IOException {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        List<Product> products = getAllProducts();

        for (Map.Entry<Long, Double> entry : idsQuantitysMap.entrySet()) {
            Long id = entry.getKey();
            Double quantity = entry.getValue();

            try {
                Product product = products.stream()
                        .filter(p -> p.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new CustomError("ERROR", "BAD REQUEST: Product with ID " + id + " not found"));

                if (product.getQuantityInStock() < quantity) {
                    throw new CustomError("ERROR", "BAD REQUEST: Not enough stock for product ID " + id);
                }

                ReceiptItem receiptItem = new ReceiptItem(product, quantity, product.getPrice());
                receiptItems.add(receiptItem);
            } catch (CustomError e) {
                errorWriter.write(e);
                System.exit(1);
            }
        }
        return receiptItems;
    }
}

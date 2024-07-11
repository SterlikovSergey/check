package ru.clevertec.check.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.check.exeption.CustomError;
import ru.clevertec.check.exeption.CustomErrorFactory;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.model.ReceiptItem;
import ru.clevertec.check.repository.ProductRepo;

import java.io.IOException;
import java.util.*;
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;




    public List<Product> getAllProducts() {
        return productRepo.getProducts();
    }

    public List<ReceiptItem> createListProductCheck(Map<Long, Double> idsQuantitysMap) throws CustomError, IOException {
        List<ReceiptItem> receiptItems = new ArrayList<>();
        List<Product> products = getAllProducts();
        for (Map.Entry<Long, Double> entry : idsQuantitysMap.entrySet()) {
            Long id = entry.getKey();
            Double quantity = entry.getValue();
            Product product = products.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new CustomError("ERROR", "BAD REQUEST: Product with ID " + id + " not found"));
            if (product.getQuantityInStock() < quantity) {
                throw CustomErrorFactory.create("ERROR", "BAD REQUEST: Not enough stock for product ID \" + id");
            }
            ReceiptItem receiptItem = new ReceiptItem(product, quantity, product.getPrice());
            receiptItems.add(receiptItem);
        }
        return receiptItems;
    }

    public Product add(Product product) {
        return product.save(product);
    }

    public Product get(Long id) {
        return productRepo.findById(id).orElseThrow();
    }

    public List<Product> getAllBy(List<Long> ids) {
        return productRepo.findAllById(ids);
    }

    public Map<Product, Integer> getCounts(List<Long> itemsIds) {
        List<Product> products = productRepo.findAllById(itemsIds);
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        for (Product product : products) {
            int count = Collections.frequency(itemsIds, product.getId());
            productIntegerMap.put(product, count);
        }
        return productIntegerMap;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

}

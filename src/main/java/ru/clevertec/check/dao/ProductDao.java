package ru.clevertec.check.dao;

import ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
}

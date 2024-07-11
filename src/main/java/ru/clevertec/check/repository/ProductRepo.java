package ru.clevertec.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.check.model.Product;

import java.util.List;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
        List<Product> getProducts();
}

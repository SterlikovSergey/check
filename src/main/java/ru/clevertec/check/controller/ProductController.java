package ru.clevertec.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.check.dto.ProductDto;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.ProductService;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        log.info("getProducts");
        List<Product> products = null;
        try {
            products = productService.getAll();
            if (products == null) {
                log.error("No products found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error getting products: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("products: {}", products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto dto){
        try {
            Product product = productMapper.toProduct(dto);
            if (product == null) {
                log.error("Error mapping product");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Product savedProduct = productService.add(product);
            if (savedProduct == null) {
                log.error("Error saving product");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error adding product: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


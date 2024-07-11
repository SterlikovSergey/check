package ru.clevertec.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {
    @GetMapping()
    public String getProductsPage() {
        return "product";
    }
}

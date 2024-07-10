package ru.clevertec.check.dao.impl;

import ru.clevertec.check.dao.ProductDao;
import ru.clevertec.check.database.DatabaseConnection;
import ru.clevertec.check.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private final DatabaseConnection dbConnection;

    public ProductDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM public.product";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long productId = resultSet.getLong("id");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                Integer quantityInStock = resultSet.getInt("quantity_in_stock");
                Boolean isWholesaleProduct = resultSet.getBoolean("wholesale_product");

                products.add(new Product(productId, description, price, quantityInStock, isWholesaleProduct));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

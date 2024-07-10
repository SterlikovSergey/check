package ru.clevertec.check.util;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.reader.impl.DiscountReader;
import ru.clevertec.check.reader.impl.ProductReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SqlQueryLoader {

    private static final String INSERT_PRODUCTS_QUERY = "INSERT INTO public.product (id, description, price, quantity_in_stock, wholesale_product) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_DISCOUNT_QUERY = "INSERT INTO public.discount_card (id, number, amount) VALUES (?, ?, ?)";

    public static void loadProductsFromFileAndInsertToDb(String filePath, Connection connection) throws IOException, SQLException {
        ProductReader productReader = new ProductReader(filePath);
        List<Product> products = productReader.readFromFile();

        try (PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCTS_QUERY)) {
            for (Product product : products) {
                statement.setLong(1, product.getId());
                statement.setString(2, product.getDescription());
                statement.setBigDecimal(3, product.getPrice());
                statement.setInt(4, product.getQuantityInStock());
                statement.setBoolean(5, product.getWholesaleProduct());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void loadDiscountFromFileAndInsertToDb(Connection connection) throws IOException, SQLException {
        DiscountReader discountReader = new DiscountReader();
        List<DiscountCard> discountCards = discountReader.readFromFile();

        try (PreparedStatement statement = connection.prepareStatement(INSERT_DISCOUNT_QUERY)) {
            for (DiscountCard discountCard : discountCards) {
                statement.setLong(1, discountCard.getId());
                statement.setInt(2, discountCard.getNumber());
                statement.setInt(3, discountCard.getDiscountAmount());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}

package ru.clevertec.check.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS public.product";
    private static final String DROP_TABLE_DISCOUNT = "DROP TABLE IF EXISTS public.discount_card";

    private static final String CREATE_TABLE_PRODUCT = """
            CREATE TABLE public.product (
                id BIGSERIAL PRIMARY KEY,
                description VARCHAR(50),
                price NUMERIC(10, 2),
                quantity_in_stock INTEGER CHECK (quantity_in_stock >= 0),
                wholesale_product BOOLEAN
            )""";

    private static final String CREATE_TABLE_DISCOUNT = """
            CREATE TABLE public.discount_card (
                id BIGSERIAL PRIMARY KEY,
                number INTEGER,
                amount SMALLINT CHECK (amount >= 0 AND amount <= 100)
            )""";

    public static void initializeDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_PRODUCT);
            statement.execute(DROP_TABLE_DISCOUNT);
            statement.execute(CREATE_TABLE_PRODUCT);
            statement.execute(CREATE_TABLE_DISCOUNT);
        }
    }
}

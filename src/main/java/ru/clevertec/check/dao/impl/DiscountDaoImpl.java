package ru.clevertec.check.dao.impl;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.dao.DiscountDao;
import ru.clevertec.check.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDaoImpl implements DiscountDao {

    private final DatabaseConnection dbConnection;

    public DiscountDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    @Override
    public DiscountCard getDiscountCardByNumber(Integer discountCard) {
        String query = "SELECT id, number, amount FROM public.discount_card WHERE number = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, discountCard);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Integer number = resultSet.getInt("number");
                Integer amount = resultSet.getInt("amount");

                return new DiscountCard(id, number, amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

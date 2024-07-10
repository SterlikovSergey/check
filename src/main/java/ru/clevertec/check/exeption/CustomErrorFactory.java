package ru.clevertec.check.exeption;

public class CustomErrorFactory {
    public static CustomError create(String code, String description) throws CustomError {
        throw new CustomError(code, description);
    }
}

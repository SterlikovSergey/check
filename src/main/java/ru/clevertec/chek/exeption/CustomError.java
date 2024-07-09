package ru.clevertec.chek.exeption;

public class CustomError extends Exception {
    private final String code;
    private final String description;

    public CustomError(String code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return description;
    }
}

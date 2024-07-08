package ru.clevertec.chek.exeption;

public class CustomError extends Exception {
    private String message;
    private String description;

    public CustomError(String message, String description) {
        super(message);
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

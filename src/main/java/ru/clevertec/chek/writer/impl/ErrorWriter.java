package ru.clevertec.chek.writer.impl;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.writer.Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorWriter implements Writer<CustomError> {
    private final String filePath;

    public ErrorWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(CustomError content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println(content.getCode());
            writer.println(content.getMessage());
        }
    }
}

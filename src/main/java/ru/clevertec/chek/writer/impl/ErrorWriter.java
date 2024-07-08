package ru.clevertec.chek.writer.impl;

import ru.clevertec.chek.exeption.CustomError;
import ru.clevertec.chek.writer.Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorWriter implements Writer<CustomError> {
    private static final String FILE_PATH = "result.csv";

    @Override
    public void write(CustomError content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, false))) {
            writer.println(content.getMessage());
            writer.println(content.getDescription());
        }
    }
}

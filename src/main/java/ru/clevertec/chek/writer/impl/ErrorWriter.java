package ru.clevertec.chek.writer.impl;

import ru.clevertec.chek.exeption.Error;
import ru.clevertec.chek.writer.Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorWriter implements Writer<Error> {
    private static final String FILE_PATH = "result.csv";

    @Override
    public void write(Error content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(content.getMessage());
            writer.println(content.getDescription());
        }
    }
}

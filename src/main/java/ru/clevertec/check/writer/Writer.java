package ru.clevertec.check.writer;

import java.io.IOException;

public interface Writer <T>{
    void write(T content) throws IOException;
}

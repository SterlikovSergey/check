package ru.clevertec.check.reader;

import java.io.IOException;
import java.util.List;

public interface Reader <T>{
    List<T> readFromFile() throws IOException;
}

package ru.clevertec.check.exeption;

import ru.clevertec.check.writer.Writer;
import ru.clevertec.check.writer.impl.ErrorWriter;

import java.io.IOException;
import java.util.logging.Logger;

public class ErrorManager {
    private static final Logger logger = Logger.getLogger("CheckLogger");
    public void handleError(CustomError error, String saveToFile) {
        /*String errorFilePath = saveToFile != null && !saveToFile.isEmpty() ? saveToFile : "result.csv";*/
        Writer<CustomError> writer = new ErrorWriter(saveToFile);
        try {
            writer.write(error);
            logger.info(error.getCode() + " " + error.getMessage());
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}

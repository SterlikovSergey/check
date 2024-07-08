package ru.clevertec.chek.writer.impl;

import ru.clevertec.chek.model.Check;
import ru.clevertec.chek.model.ReceiptItem;
import ru.clevertec.chek.writer.Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class CheckWriter implements Writer<Check> {
    private static final String FILE_PATH = "result.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final Logger logger = Logger.getLogger("CheckLogger");

    @Override
    public void write(Check content) throws IOException {
        StringBuilder checkInfo = new StringBuilder();

        checkInfo.append("Date;Time\n");
        checkInfo.append(content.getCheckDate().format(DATE_FORMATTER)).append(";").append(content.getCheckTime().format(TIME_FORMATTER)).append("\n");
        checkInfo.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");

        for (ReceiptItem item : content.getReceiptItems()) {
            checkInfo.append(String.format("%.0f;%s;%.2f$;%.2f$;%.2f$\n",
                    item.getQuantity(),
                    item.getProduct().getDescription(),
                    item.getPrice(),
                    item.getDiscount(),
                    item.getTotal()));
        }

        if (content.getDiscountCard() != null) {
            checkInfo.append("DISCOUNT CARD;DISCOUNT PERCENTAGE\n");
            checkInfo.append(content.getDiscountCard()).append(";").append(content.getDiscountPercentage()).append("%\n");
        }
        checkInfo.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        checkInfo.append(String.format("%.2f$;%.2f$;%.2f$\n",
                content.getTotalPrice(),
                content.getTotalDiscount(),
                content.getTotalWithDiscount()));

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, false))) {
            writer.print(checkInfo);
        }
        logger.info(checkInfo.toString());
    }
}



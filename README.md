Приложение запускается с помощью консольной команды:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx pathToFile=XXXX saveToFile=xxxx
либо передать в параметры аргумента: 3-7 6-2 2-5 5-1 20-5 4-1 2-1 discountCard=1111 balanceDebitCard=500 pathToFile=/Users/sergeysterlikov/Desktop/products.csv saveToFile=/Users/sergeysterlikov/Desktop/result.csv
Пример с ошибкой 1: должен создаться файл result.csv в корне проекта с ошибкой BAD REQUEST:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=100 pathToFile=./products.csv
Пример с ошибкой 2: должен создаться файл error_result.csv в корне проекта с ошибкой BAD REQUEST:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=12.1 saveToFile=./error_result.csv
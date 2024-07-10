Стек: Gradle 8.5
В качестве DB использовать PostgreSQL
Разрешено использовать только JDBC (org.postgresql.Driver)
Замена хранения исходных данных (схема 8, 9) в файлах на хранение в
таблицах PostgreSQL: product и discount_card
Убран параметр pathToFile
Настройки подключения к БД передавать через аргументы командной строки на
вход добавлены параметры: (Обязательные)
datasource.url=ххх datasource.username=ххх datasource.password=ххх
Пример:
java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres
DDL/DML операции должны храниться в файле src/main/resources/data.sql (но не использовать)
Покрыть функционал юнит-тестами (не менее 70 %)
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100

По команде выше должен сформироваться CSV-файл (result.csv) в корне проекта, содержащий в себе список товаров и их количество с ценой, а также рассчитанную сумму с учетом скидки по предъявленной карте, если она есть.

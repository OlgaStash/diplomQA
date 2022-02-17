## Диплом по курсу "Тестировщик ПО"

## Инструкция по запуску авто-тестов:
1. Скачать репозиторий [diplomQA](https://github.com/OlgaStash/diplomQA.git), разархивировать его и открыть в IntelliJ IDEA.
2. Запустить контейнеры Node.js, MySql, PostgreSQL выполнив команду  <code>docker-compose up</code>.
3. Запустить приложение:
- для базы данных MySQL выполнив команду:  <code>java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/app</code>;

- для базы данных PostgreSQL выполнив команду:  <code>java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres</code>.
4. Запустить тесты выполнив команду: <code>.\gradlew test</code>.

### Необходимое ПО:
1. Docker 
2. IntelliJ IDEA Ultimate
3. Google Chrome
4. Архиватор типа Zip

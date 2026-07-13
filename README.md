# DaData API Tests

Проект содержит автоматизированные тесты API сервиса DaData, написанные с использованием Java, JUnit 5 и Rest Assured.

## Используемые технологии

- Java 21
- Maven
- JUnit 5
- Rest Assured
- Jackson

## Запуск тестов

### Запуск всех тестов

```bash
mvn test
```

### Запуск одного тестового класса

```bash
mvn -Dtest=PostTest test
```

или

```bash
mvn -Dtest=GetTest test
```

или

```bash
mvn -Dtest=NegativeTest test
```

### Запуск одного тестового метода

```bash
mvn -Dtest=PostTest#testSuggestAddress test
```

## Конфигурация

Перед запуском необходимо указать:

- API Token
- настройки в `config.properties`

## Структура проекта

- `api` — тесты API;
- `dto` — DTO-классы для сериализации и десериализации JSON;
- `constants` — эндпоинты API;
- `specs` — спецификации Rest Assured;
- `utils` — чтение конфигурации.
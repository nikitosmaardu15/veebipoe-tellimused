# Veebipoe tellimused

REST API veebipoe tellimuste kuvamiseks. Iga tellimus tagastatakse koos toodetega JSON kujul.

## Tehnoloogiad

Java 25, Spring Boot 4, PostgreSQL 18, Flyway, Docker Compose, Testcontainers, Gradle.

## Käivitamine

Vajalik: JDK 25 ja töötav Docker.

```bash
./gradlew bootRun
```

Windowsis: `gradlew.bat bootRun`

PostgreSQL käivitub Dockeris automaatselt, Flyway loob tabelid ja näidisandmed. Rakendus töötab aadressil `http://localhost:8080`.

## API

| Meetod | URL | Kirjeldus |
|---|---|---|
| GET | `/api/v1/orders` | Tellimuste nimekiri koos toodetega (`page`, `size`, `sort`) |
| GET | `/api/v1/orders/{id}` | Üks tellimus, olematu korral 404 |

Näited on failis `requests.http`.

## Andmebaas

- `orders`: tellimuse number, staatus, loomise aeg
- `order_items`: nimetus, kogus, ühiku hind; viitab tellimusele välisvõtmega `order_id`

## Testid

```bash
./gradlew test
```
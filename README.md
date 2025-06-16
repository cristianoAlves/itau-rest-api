# rest-API-example
## Business rules:
Only transactions with these rules will be accepted:

1. All fields were sent
2. Transaction cannot be created with future dates
3. Transactions can be any time in the past
4. Transactions cannot have negative values
5. Transactions value must be igual or greater than zero

## Endpoints
### Make a transaction
 `POST /transacao`

```json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

Fields:

| Field      | Meaning                                         | Mandatory? |
|------------|-------------------------------------------------|------------|
| `valor`    | Transaction value                               | yes        |
| `dataHora` | Timestamp that when the transaction was created | yes        |

Response:
- `201 Created` no body 
- `422 Unprocessable Entity` no body 
- `400 Bad Request` no body

### Delete transactions: 
`DELETE /transacao`

It will delete all transactions

Response:
- `200 OK` no body


### Statistics: 
`GET /estatistica`

Should return the statistics that occurred in the last 60 seconds

```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

Fields:

| Field   | meaning                                                    | Mandatory? |
|---------|------------------------------------------------------------|------------|
| `count` | Amount of transactions in the last 60 seconds              | yes        |
| `sum`   | Sum of all transactions in the last 60 seconds             | yes        |
| `avg`   | Average amount of transactions in the last 60 seconds      | yes        |
| `min`   | Transaction with lowest value in the last 60 seconds       | yes        |
| `max`   | Transactions with the highest value in the last 60 seconds | yes        |

Response:

- `200 OK`

## Technologies
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
* [Maven](https://maven.apache.org/)
* [Mapstruct](https://mapstruct.org/)
* [Docker compose](https://docs.docker.com/compose/)
* [Springdoc](https://springdoc.org/)
* [Spring Actuator](https://docs.spring.io/spring-boot/docs/2.0.x/actuator-api/html/) as part of Observability

## Architecture
Based on Layered Architecture
```
src/main/java
└── com/itau/example
    |── config
    |── controller
    │── domain
    │   ├── dto
    │   ├── entity
    │   ├── mapper
    │   ├── repository
    │   ├── request
    │   ├── service
    │   └── validador
    └── ExampleApplication.java
```

## Running the application using docker compose
### Clone this repository:
```
git clone git@github.com:cristianoAlves/itau-rest-api.git
```
### From app root directory:
```
docker compose up -d --build
```
### Test using actuator
`curl http://localhost:8080/actuator`

### API documentation
http://localhost:8080/swagger-ui/index.html
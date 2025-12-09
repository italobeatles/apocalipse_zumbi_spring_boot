# apocalipse_zumbi_spring_boot

API REST do apocalipse zumbi em Spring Boot 3 / Java 21, espelhando os mesmos endpoints do projeto Laravel.

Endpoints principais:
- `GET /sobreviventes`, `POST /sobreviventes`, `GET/PUT/DELETE /sobreviventes/{id}`
- `GET /inventario`, `GET /inventario/{id}`, `POST /inventario/troca`
- `POST /informar-zumbificacao`
- `GET /relatorio-geral`
- Swagger UI: `/swagger`

Config:
- `src/main/resources/application.yml` usa MySQL (ajuste usuário/senha).
- `src/test/resources/application-test.yml` usa SQLite para testes rápidos.

Rodar local: `./mvnw spring-boot:run`

Docker: `docker compose up --build`

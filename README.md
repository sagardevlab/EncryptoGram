# Encryption Service

## Project Overview

- **Name:** encryption
- **Description:** A small Spring Boot application that provides simple text transformation endpoints (encrypt/decrypt) using a Caesar cipher-style shift.

## Tech Stack

- Java 21
- Spring Boot 4.0.4 (spring-boot-starter-parent)
- Spring Web MVC (`spring-boot-starter-webmvc`)
- Spring Data JPA (`spring-boot-starter-data-jpa`) (present but not used for encryption logic)
- H2 in-memory database (runtime)
- Lombok (compile-time / annotation processing)
- Build: Maven

## Repository Structure

- `src/main/java/com/sagardevlab/data/encryption/EncryptionApplication.java` - Spring Boot entry point
- `src/main/java/com/sagardevlab/data/encryption/controller/EncryptionController.java` - REST controller with endpoints
- `src/main/java/com/sagardevlab/data/encryption/service/EncryptionService.java` - Core encryption/decryption logic
- `src/main/java/com/sagardevlab/data/encryption/model/EncryptionRequest.java` - Request model
- `src/main/java/com/sagardevlab/data/encryption/model/EncryptionResponse.java` - Response model
- `src/main/resources/application.properties` - app properties (H2 datasource + app name)
- `pom.xml` - Maven project descriptor
- `HELP.md` - reference docs and notes

## How it Works (Implementation Details)

- The transformation is a simple Caesar cipher (alphabetic rotation) implemented in `EncryptionService`.
- Constant `SHIFT = 5` is used for encryption. Decryption uses `26 - (SHIFT % 26)` to reverse.
- Only alphabetic characters are shifted; other characters (spaces, punctuation, numbers) are preserved.

## API Endpoints

Base path: `/api`

1) Encrypt
- Path: `/api/encrypt`
- HTTP method: POST
- Request body (JSON):

```json
{ "text": "hello world" }
```

- Response (JSON):

```json
{
  "originalText": "hello world",
  "transformedText": "mjqqt btwqi",
  "operation": "encrypt"
}
```

2) Decrypt
- Path: `/api/decrypt`
- HTTP method: POST
- Request body (JSON):

```json
{ "text": "mjqqt btwqi" }
```

- Response (JSON):

```json
{
  "originalText": "mjqqt btwqi",
  "transformedText": "hello world",
  "operation": "decrypt"
}
```

Validation and error responses
- Requests: `text` is required and must not be blank (`@NotBlank` on `EncryptionRequest.text`).
- The application uses a global `GenericExceptionHandler` to return consistent JSON error payloads.

Example error (validation failure):

```json
{
  "timestamp": 1670000000000,
  "status": 400,
  "error": "Validation Failed",
  "message": "text: text must not be blank",
  "path": "/api/encrypt"
}
```

Example error (malformed JSON):

```json
{
  "timestamp": 1670000000000,
  "status": 400,
  "error": "Malformed JSON",
  "message": "Unexpected end-of-input...",
  "path": "/api/encrypt"
}
```

Notes
- Endpoints were changed from `GET` to `POST` to follow HTTP semantics (bodies on POST). Update any clients accordingly.

## Example requests (curl)

Start the app (default port 8080) and run:

```bash
# build
mvn clean package

# run
mvn spring-boot:run
```

Then (if your `curl` supports a body for GET):

```bash
curl -i -X GET http://localhost:8080/api/encrypt \
  -H "Content-Type: application/json" \
  -d '{"text":"Hello World"}'

curl -i -X GET http://localhost:8080/api/decrypt \
  -H "Content-Type: application/json" \
  -d '{"text":"Mjqqt Btwqi"}'
```

Recommended (change controller to POST) and use:

```bash
curl -i -X POST http://localhost:8080/api/encrypt \
  -H "Content-Type: application/json" \
  -d '{"text":"Hello World"}'
```

## Build & Run

- Requirements: JDK 21, Maven
- Build: `mvn clean package`
- Run: `mvn spring-boot:run` or run the generated jar `java -jar target/encryption-0.0.1-SNAPSHOT.jar`
- Tests: `mvn test` (there is a basic context load test in `EncryptionApplicationTests`)

## Configuration

- `src/main/resources/application.properties` contains:
  - `spring.application.name=encryption`
  - H2 datasource settings for an in-memory DB (unused by current logic)

## Suggested Improvements

- Change endpoints to `POST` and add validation (e.g., non-null `text`).
- Add controller-level exception handling and input validation.
- Add OpenAPI/Swagger documentation for clarity and developer UX.
- Add unit tests for `EncryptionService` (encrypt/decrypt and edge cases).
- Remove unused JPA dependency if persistence is not needed, or add repository layer if it is.
- Consider configuration for the shift value (externalize `SHIFT` into `application.properties` or a `@ConfigurationProperties`).

## Tests

- `src/test/java/.../EncryptionApplicationTests.java` contains a single `contextLoads()` test. Run with `mvn test`.

## License & Authors

- No license is specified in the POM. Add a `LICENSE` file if you intend to open-source this project.
- Project metadata fields in `pom.xml` (name, description, developers, license) are empty placeholders.

## Contact / Next Steps

- File created: [README.md](README.md)
- Suggested next steps: change endpoints to `POST`, add service unit tests, and add README badges (build/test).


Expense Tracker CLI
====================

A small command-line expense tracker built with Spring Boot and Spring Shell. It persists data in an on-disk H2 database
and provides simple commands to add, list, summarize, update, and delete expenses.


Overview
--------

- Stack: Java 21, Spring Boot 3.5, Spring Shell 3, Spring Data JPA, H2 (file mode), Lombok, MapStruct
- Build tool: Gradle (Wrapper included)
- Entry point: `ua.azaika.expensetrackercli.ExpenseTrackerCliApplication`
- CLI is interactive using Spring Shell; run the app and type commands at the prompt.

Requirements
------------

- Java 21 (JDK)
- Internet access for dependency download on first build
- macOS/Linux/Windows supported via Gradle Wrapper (`./gradlew` or `gradlew.bat`)

Getting Started
---------------

Clone the repository, then build and run using the Gradle Wrapper.

Build the project:

```
./gradlew build
```

Run in interactive CLI mode (recommended during development):

```
./gradlew bootRun
```

Alternatively, run the fat JAR after building:

```
./gradlew bootJar
java -jar build/libs/expense-tracker-cli-1.0.0.jar
```

Once started, you’ll enter a Spring Shell prompt. Type `help` to see available commands.


Commands (Spring Shell)
-----------------------

The following commands are available from the interactive shell. Short options shown below are defined via Spring Shell
annotations where present.

- Add an expense
    - Command: `add`
    - Options:
        - `-d <description>` (required)
        - `-a <amount>` (required, decimal)
    - Example:
        - `add -d "Coffee" -a 3.50`

- List all expenses
    - Command: `list`
    - Example:
        - `list`

- Summary (sum of all transactions, or by month)
    - Command: `summary`
    - Options:
        - `-m <MONTH>` (optional; Java `Month` enum name, e.g., `JANUARY`)
    - Examples:
        - `summary`
        - `summary -m JANUARY`

- Update an expense by id (updates description or amount)
    - Command: `update`
    - Options:
        - `-i <id>` (required)
        - `-d <description>` (optional)
        - `-a <amount>` (optional)
    - Example:
        - `update -i 5 -d "Lunch"`
        - `update -i 5 -a 12.25`

- Delete an expense by id
    - Command: `delete`
    - Example:
        - TODO: verify exact option/argument style for delete (no `@Option` annotation present). Try `delete --id 5` or
          `delete 5` in the shell and update this README accordingly.

Notes:

- `update` currently applies either description or amount (whichever is provided first) due to service logic; providing
  both at once will update only one field.

Data Storage
------------

- Database: H2 in file mode
- Location: `./data/expenses_db` (configured via `application.yml`)
- Tables are created/updated automatically with `spring.jpa.hibernate.ddl-auto=update`.

Configuration and Environment Variables
---------------------------------------

Default configuration is in `src/main/resources/application.yml`:

```
spring:
  application:
    name: expense-tracker-cli
  shell:
    interactive:
      enabled: true
  datasource:
    url: jdbc:h2:file:./data/expenses_db
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
```

You can override any Spring property via environment variables or command-line arguments, for example:

- Env var (Unix): `SPRING_DATASOURCE_URL=jdbc:h2:file:/absolute/path/expenses_db ./gradlew bootRun`
- JVM arg: `./gradlew bootRun --args='--spring.datasource.url=jdbc:h2:file:./data/expenses_db'`

Build Scripts and Useful Gradle Tasks
-------------------------------------

- `./gradlew tasks` – list available tasks
- `./gradlew build` – compile, run tests, and build artifacts
- `./gradlew test` – run tests
- `./gradlew bootRun` – run the interactive CLI
- `./gradlew bootJar` – create executable Spring Boot JAR under `build/libs`

Testing
-------

The project uses JUnit 5 with Spring Boot Test. A basic context load test exists at:

- `src/test/java/ua/azaika/expensetrackercli/ExpenseTrackerCliApplicationTests.java`

Run tests:

```
./gradlew test
```

Project Structure
-----------------

High-level layout:

```
.
├─ build.gradle
├─ settings.gradle
├─ gradlew / gradlew.bat
├─ gradle/wrapper/
├─ src/
│  ├─ main/java/ua/azaika/expensetrackercli/
│  │  ├─ ExpenseTrackerCliApplication.java        # Spring Boot entry point (@SpringBootApplication, @CommandScan)
│  │  ├─ commands/GeneralCommands.java            # CLI commands (add, list, summary, update, delete)
│  │  ├─ services/ExpenseService.java             # Business logic
│  │  ├─ repository/TransactionRepository.java    # JPA repository
│  │  ├─ model/TransactionEntity.java             # JPA entity
│  │  ├─ model/TransactionDTO.java                # DTO (record)
│  │  └─ mapper/TransactionMapper.java            # MapStruct mapper
│  └─ main/resources/application.yml              # Spring config (H2 db, shell)
├─ data/expenses_db.*                             # H2 database files (created at runtime)
├─ expense-tracker-cli.log                        # Application log (if configured)
├─ HELP.md                                        # Links to docs
└─ README.md
```

Logging
-------

An `expense-tracker-cli.log` file is present at the project root. If needed, configure logging via `application.yml` or
`logback-spring.xml` to control log output and rotation. TODO: document logging configuration once finalized.


License
-------

TODO: Add a license (e.g., MIT, Apache-2.0) and include a `LICENSE` file. Update this section accordingly.


Roadmap / TODOs
---------------

- Confirm and document exact syntax for `delete` command in Spring Shell.
- Add richer tests (service layer and command integration).
- Consider allowing simultaneous update of description and amount.
- Document logging configuration and levels.
- Provide distribution instructions (e.g., Homebrew scoop/chocolatey if desired).

Acknowledgements
----------------

- Spring Boot and Spring Shell documentation
- H2 Database
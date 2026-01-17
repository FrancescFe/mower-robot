# VW DIGITAL:HUB Mower Robot Challenge

| Name     | Surname      |
|----------|--------------|
| Francesc | Ferrer Rubio |

## Technologies Used

- **Kotlin 2.2** - Modern JVM language with null safety and functional programming support
- **Gradle 8.14** - Build automation tool with Kotlin DSL
- **JUnit 5** - Testing framework
- **Spotless + Ktlint** - Static code formatting
- **GitHub Actions** - CI/CD pipeline

## Technical Decisions

### Spring Boot discarded
Although Spring Boot is a powerful framework for building Java/Kotlin applications, it was discarded for this project because:
- It introduces innecessary complexity for achieve the project requirements
- The solution has been intentionally developed framework-agnostic
- Keeps the focus on domain logic rather than infrastructure concerns

### Domain-Driven Design (DDD)

The project follows DDD principles with a single bounded context representing the mower navigation domain.
The domain layer is structured into cohesive subdomains:
```
src/main/kotlin
└── org/francescfe/mowerrobot
    └── domain
        ├── mower
        │   ├── Mower.kt
        │   └── Instruction.kt
        ├── grid
        │   └── Grid.kt
        └── spatial
            ├── Position.kt
            └── Orientation.kt

```

### Design Principles & Patterns

#### SOLID Principles

| Principle                 | Application                                                                       |
|---------------------------|-----------------------------------------------------------------------------------|
| **Single Responsibility** | Each class has one reason to change                                               |
| **Open/Closed**           | Domain models are open for extension through composition, closed for modification |
| **Liskov Substitution**   | Value objects maintain consistent behavior                                        |
| **Interface Segregation** | TBD                                                                               |
| **Dependency Inversion**  | Domain layer has no external dependencies                                         |

#### Applied Patterns

- **Value Objects**: Core domain concepts are immutable value objects that encapsulate domain concepts
- **Immutability**: All domain objects are immutable; operations return new instances rather than modifying state
- **Inline Value Class**: `Instruction` uses Kotlin's `@JvmInline value class` for type safety without runtime overhead
- **Factory Methods**: Static factory methods (e.g., `Instruction.fromChar()`) provide controlled object creation
- **Fail Fast**: Invalid instructions are rejected at construction time using domain-specific exceptions
- **Object Mother** (testing): Test fixtures provide semantic factory methods for creating test objects

### Grid and Movement Decisions

- The Grid is modeled as an immutable value object representing the workspace boundaries. It defines the upper-right coordinates of the allowed area.
- The Grid exposes a pure validation method `isWithinBounds(position)`, does not control movement nor throw exceptions.
- Movement decisions remain fully encapsulated within the Mower aggregate, which evaluates candidate positions and decides whether to apply or ignore a movement based on grid constraints.

### Static Code Formatter
Spotless has been integrated as a static code formatting tool to ensure consistent code style across the project.
Ktlint is used as the underlying formatter, as it is the de-facto standard in the Kotlin ecosystem.
This setup ensures a consistent style and reduces noise in code reviews.

```bash
# Apply formatting
./gradlew spotlessApply
```

### Include CI pipeline
A basic CI pipeline has been configured using GitHub Actions to ensure code quality on every pull request:
- Automatic code format validation (`spotlessCheck`)
- Build validation and test execution
- Concurrency control: new commits cancel in-progress runs
- Only executes on ready-for-review PRs (draft PRs are skipped)

### Git & GitHub Workflow
This project follows a structured Git workflow designed for traceability and clean history:
1. Planning with GitHub Projects: A GH Projects board is used to organize and visualize work
2. Milestones: Work is grouped into milestones representing deliverable increments, providing a clear view of progress toward goals.
3. Issues: Each task is tracked as a GitHub Issue
4. Branch Strategy
   - Naming convention: <issue-number>_short_description (e.g., 005_add_mower_model)
   - Each branch addresses a single issue
   - Keeps changes focused and reviewable
5. Pull Requests & Squash Merge
   - All changes are integrated via Pull Requests linked to their originating issue
   - Squash and Merge strategy is used to:
     - Maintain a clean, linear commit history on main
     - Consolidate WIP commits into a single meaningful commit

## Assumptions
- Invalid instructions are considered domain errors and cause the system to fail fast.
- The domain model is designed to be framework-agnostic and independent of any input/output or infrastructure concerns.
- Grid size validation is treated as a configuration concern. Since an invalid grid prevents the system from functioning at all, standard argument validation is used instead of domain-specific exceptions.
- Attempting to move outside the grid is considered a valid boundary condition and is handled as part of normal domain behavior.

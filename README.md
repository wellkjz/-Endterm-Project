 Dragon Management System
 
 A. Project OverviewThis project is a sophisticated RESTful API built with Spring Boot, migrated from a legacy console application. It serves as a management system for a dragon registry, tracking species, stamina, and pricing. The system architecture is designed to be extensible, following modern backend standards and the concepts explored during the BioSense Rover robotics project in February 2026.
 
 B. REST API DocumentationBase URL: http://localhost:8080/api/dragonsSample Response (JSON):(Postman screenshots can be found in /docs/screenshots/)
 
 C. Design Patterns SectionSingleton Pattern: Implemented in the SystemLogger class to provide a single, globally accessible logging service for tracking application events.Factory Pattern: The DragonFactory class encapsulates the creation logic for FireBreather and LightningStriker subclasses, allowing the repository to instantiate objects based on database records dynamically.Builder Pattern: Used via DragonBuilder to construct complex dragon entities step-by-step, ensuring a fluent and readable object creation process in the service layer.
 
 D. Component Principles SectionThe project structure adheres to Component Principles to ensure high maintainability:REP (Reuse/Release Equivalence): Reusable modules like repository and service are clearly isolated.CCP (Common Closure Principle): Classes that change together (e.g., database models and their repositories) are grouped within the same package.CRP (Common Reuse Principle): The architecture ensures that a change in the controller layer does not force a change in the model layer unless necessary.
 
 E. SOLID & OOP SummarySingle Responsibility: Each class has one job (e.g., DragonRepository only handles SQL).Open/Closed: New dragon species can be added to the Factory without modifying the existing Service logic.Liskov Substitution: FireBreather and LightningStriker can replace DragonBase without breaking the system.Polymorphism: Overridden methods like calculateFinalPrice() provide unique behavior for different species.
 
 F. Database SchemaThe system utilizes a PostgreSQL database with a schema optimized during the February 2026 database project phase:Table: dragonsColumns: id (Serial PK), name (Varchar Unique), type (Varchar), stamina (Int), base_price (Decimal).
 
 G. System Architecture DiagramThe system follows a classic Layered Architecture:Client $\rightarrow$ REST Controller $\rightarrow$ Service $\rightarrow$ Repository $\rightarrow$ PostgreSQL DB
 
 H. Instructions to RunEnsure PostgreSQL is running and the database is created.Update src/main/resources/application.properties with your credentials.Run the application using Maven:Access the API at http://localhost:8080/api/dragons.
 
 I. Reflection SectionThis project represented a significant shift from console-based logic to a distributed REST architecture. Implementing the Dragon Exception Hierarchy in February 2026 allowed for robust error handling, specifically catching database conflicts. Integrating design patterns like Builder and Factory taught me how to manage object complexity while maintaining a clean, professional codebase.

 Dragon Management System
 
 A. Project Overview
 This project is a sophisticated RESTful API built with Spring Boot, migrated from a legacy console application. It serves as a management system for a dragon registry, tracking species, stamina, and pricing.
 
 B. REST API Documentation
 Base URL: http://localhost:8080/api/dragons
 Method,Endpoint,Description,Sample JSON Request
GET,/api/dragons,Retrieve all dragons,N/A
GET,/api/dragons/{id},Get specific dragon,N/A
POST,/api/dragons,Create a new dragon,"{""name"": ""Toothless"", ""type"": ""Lightning"", ""price"": 5000}"
PUT,/api/dragons/{id},Update dragon details,"{""name"": ""Stormfly"", ""price"": 4500}"
DELETE,/api/dragons/{id},Remove a dragon,N/A
Sample Response (JSON):{
  "id": 1,
  "name": "Toothless",
  "type": "Strike Class",
  "price": 7500.0,
  "status": "AVAILABLE"
}(Postman screenshots can be found in /docs/screenshots/)
 
 C. Design Patterns Section
 Singleton Pattern: Implemented in the SystemLogger class to provide a single, globally accessible logging service for tracking application events.Factory Pattern: The DragonFactory class encapsulates the creation logic for FireBreather and LightningStriker subclasses, allowing the repository to instantiate objects based on database records dynamically.Builder Pattern: Used via DragonBuilder to construct complex dragon entities step-by-step, ensuring a fluent and readable object creation process in the service layer.
 
 D. Component Principles Section
 The project structure adheres to Component Principles to ensure high maintainability:REP (Reuse/Release Equivalence): Reusable modules like repository and service are clearly isolated.CCP (Common Closure Principle): Classes that change together (e.g., database models and their repositories) are grouped within the same package.CRP (Common Reuse Principle): The architecture ensures that a change in the controller layer does not force a change in the model layer unless necessary.
 
 E. SOLID & OOP Summary
 Single Responsibility: Each class has one job (e.g., DragonRepository only handles SQL).Open/Closed: New dragon species can be added to the Factory without modifying the existing Service logic.Liskov Substitution: FireBreather and LightningStriker can replace DragonBase without breaking the system.Polymorphism: Overridden methods like calculateFinalPrice() provide unique behavior for different species.
 
 F. Database Schema
 The system utilizes a PostgreSQL database with a schema optimized during the February 2026 database project phase:Table: dragonsColumns: id (Serial PK), name (Varchar Unique), type (Varchar), stamina (Int), base_price (Decimal).
 
 G. System Architecture Diagram
 The system follows a classic Layered Architecture:Client $\rightarrow$ REST Controller $\rightarrow$ Service $\rightarrow$ Repository $\rightarrow$ PostgreSQL DB
 
 H. Instructions to Run
 Ensure PostgreSQL is running and the database is created.Update src/main/resources/application.properties with your credentials.Run the application using Maven:Access the API at http://localhost:8080/api/dragons.
 
 I. Reflection Section
 This project represented a significant shift from console-based logic to a distributed REST architecture. Implementing the Dragon Exception Hierarchy in February 2026 allowed for robust error handling, specifically catching database conflicts. Integrating design patterns like Builder and Factory taught me how to manage object complexity while maintaining a clean, professional codebase.

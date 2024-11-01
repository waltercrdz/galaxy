# Galaxy

### How to run

This application was developed with Spring Boot 1.3.5.RELEASE and MongoDB. You can run it with the following commands:

> mvn clean package

> java -jar target/galaxy-1.0.0-SNAPSHOT.jar

### Project Description

In a distant galaxy, there are three civilizations: Vulcans, Ferengis, and Betasoides. Each civilization lives in peace on its respective planet and masters weather prediction through a complex computer system.

### Assumptions

- The planet Ferengi moves with an angular velocity of 1 degree/day clockwise. Its distance from the sun is 500Km.
- The planet Betasoide moves with an angular velocity of 3 degrees/day clockwise. Its distance from the sun is 2000Km.
- The planet Vulcano moves with an angular velocity of 5 degrees/day counterclockwise. Its distance from the sun is 1000Km.
- All orbits are circular.

### Weather Conditions

- **Drought period**: When the three planets are aligned with each other and with the sun.
- **Rain period**: When the three planets form a triangle and the sun is inside the triangle. The peak intensity occurs when the perimeter of the triangle is at its maximum.
- **Optimal pressure and temperature conditions**: When the three planets are aligned with each other but not with the sun.

### API Objectives

1. Predict drought periods in the next 10 years.
2. Predict rain periods and determine the day of peak rain intensity in the next 10 years.
3. Predict periods of optimal pressure and temperature conditions in the next 10 years.

### Bonus

To use the system as a service for other civilizations, the Vulcans require:

1. Generate a data model with weather conditions for every day up to 10 years in advance using a job to calculate them.
2. Generate a REST API that returns the weather condition of the queried day in JSON format.
3. Host the data model and REST API on a free cloud computing service (such as APP Engine or Cloud Foundry) and provide the URL for queries.

Example query:
```
GET → http://.../weather?day=566 → Response: {“day”:566, “weather”:”rain”}
```
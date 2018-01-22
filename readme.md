# Gardirop App
The simplest e-commerce app. 

#### Maven commands:
- liquibase-diff-generate: 
  ```
  mvn package -DskipTests=true spring-boot:start -Dspring.config.name=application-liquibase-diff
  mvn clean liquibase:diff
  ```
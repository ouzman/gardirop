# Gardirop App
The simplest e-commerce app. 

#### Maven commands:
- liquibase-diff-generate: 
  ```
  mvn clean package spring-boot:start -Dspring.config.name=application-liquibase-diff
  mvn liquibase:diff
  ```
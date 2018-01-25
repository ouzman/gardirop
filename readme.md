# Gardirop App
The simplest e-commerce app. 

#### Maven commands:
- liquibase-diff-generate: 
  ```
  mvn -DskipTests package spring-boot:start -Dspring.profiles.active=default,liquibase-diff
  mvn clean liquibase:diff
  ```
- Deploy to ElasticBeanstalk 
  ```
  mvn clean install beanstalk:upload-source-bundle beanstalk:create-application-version beanstalk:update-environment beanstalk:wait-for-environment
  ```
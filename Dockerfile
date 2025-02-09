FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvm clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/student-management-system-0.0.1-SNAPSHOT.jar student.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","student.jar" ]

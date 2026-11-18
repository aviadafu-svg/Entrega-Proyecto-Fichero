
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve


COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/app-fat.jar app.jar

COPY reservas_mockaroo_iso.csv .

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "app.jar"]
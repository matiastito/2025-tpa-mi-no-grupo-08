# ==========================
# Etapa 1: Build del JAR con Maven
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar los archivos de configuración de Maven
COPY pom.xml .
COPY src ./src
COPY ../pom.xml parent-pom.xml

# Compilar el proyecto (sin tests)
RUN mvn clean -f parent-pom.xml install -N -DskipTests
RUN mvn clean package -DskipTests

# ==========================
# Etapa 2: Imagen final (solo el JAR)
# ==========================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el JAR compilado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
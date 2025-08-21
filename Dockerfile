# Usa una imagen oficial de Java
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copia el pom.xml y descarga las dependencias primero (para caching)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copia el código fuente
COPY src/ src/

# Compila el proyecto y crea el JAR
RUN ./mvnw package -DskipTests

# Puerto que expone la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "target/auth-microservice-0.0.1-SNAPSHOT.jar"]
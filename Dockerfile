# Use OpenJDK 17
FROM eclipse-temurin:21-jdk-alpine

# Cria diretório para o app
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Expõe porta que o Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar
CMD ["java", "-jar", "target/jira-0.0.1-SNAPSHOT.jar"]
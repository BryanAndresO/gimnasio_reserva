# ========================================
# Etapa 1: Build
# ========================================
FROM gradle:8.14-jdk17 AS builder

WORKDIR /app

# Copiar archivos de configuración de Gradle primero (para aprovechar cache)
COPY build.gradle settings.gradle ./
COPY gradle gradle

# Copiar código fuente
COPY src src

# Construir la aplicación (sin ejecutar tests para acelerar el build)
RUN gradle clean bootJar --no-daemon

# ========================================
# Etapa 2: Run
# ========================================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto (Render usa la variable PORT)
EXPOSE 8080

# Variables de entorno por defecto (se sobrescriben en Render)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

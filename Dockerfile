# Multi-stage Dockerfile to build frontend (Vite) and backend (Spring Boot) into a single image
# Usage: build from repo root so both Frontend-gimnasio and gimnasioreserva-spring folders are available

# ----------------------------------------
# Stage 1 - Build frontend
# ----------------------------------------
FROM node:20 AS frontend-builder
WORKDIR /workspace/frontend

# Copy frontend sources
COPY Frontend-gimnasio/package*.json ./
COPY Frontend-gimnasio/package-lock.json* ./
COPY Frontend-gimnasio/ ./

# Install and build frontend
RUN npm ci --silent && npm run build --silent

# ----------------------------------------
# Stage 2 - Build backend (Gradle) and embed frontend
# ----------------------------------------
FROM gradle:8.14-jdk17 AS builder
WORKDIR /workspace/backend

# Copy Gradle config and wrapper
COPY gimnasioreserva-spring/build.gradle gimnasioreserva-spring/settings.gradle ./
COPY gimnasioreserva-spring/gradle ./gradle

# Copy backend source
COPY gimnasioreserva-spring/src ./src
COPY gimnasioreserva-spring/gradlew ./gradlew
COPY gimnasioreserva-spring/gradle ./gradle

# Copy built frontend into Spring Boot static resources so Spring serves it
# (frontend build output in frontend-builder is at /workspace/frontend/dist)
RUN mkdir -p src/main/resources/static
COPY --from=frontend-builder /workspace/frontend/dist ./src/main/resources/static

# Build the Spring Boot jar
RUN gradle clean bootJar --no-daemon

# ----------------------------------------
# Stage 3 - Runtime image
# ----------------------------------------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from builder
COPY --from=builder /workspace/backend/build/libs/*.jar app.jar

# Expose default port (Render will provide $PORT)
EXPOSE 8080

# JVM options default
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Start application binding to the platform provided PORT (fallback 8080)
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar app.jar"]

# Adjust DOTNET_OS_VERSION as desired
ARG JAVA_OS_VERSION="-alpine"
ARG JAVA_VERSION=21

# --- Stage 1: Fetch Dependencies ---
# Use an official Gradle image that includes a JDK.
# Choose a version that matches your project's requirements.
FROM gradle:8.14.1-jdk${JAVA_VERSION}${JAVA_OS_VERSION} AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the files necessary to download dependencies.
# This leverages Docker's layer caching. If these files don't change,
# this layer won't be rebuilt.
COPY build.gradle.kts .
COPY settings.gradle.kts .
# If you have a gradle.properties file, copy it too
# COPY gradle.properties .
# If you use a libs.versions.toml (for version catalogs)
# COPY gradle/libs.versions.toml ./gradle/

# Run the command to download dependencies.
# The `--build-cache` option can speed up subsequent builds if you have a shared build cache.
# The `dependencies` task (or `assemble --no-daemon --stacktrace -x test` or similar that resolves dependencies)
# will download all necessary dependencies.
# RUN gradle dependencies --no-daemon --stacktrace || true
# The `|| true` is a common pattern to ensure this layer completes even if there are
# minor warnings or if the `dependencies` task itself doesn't "succeed" in a way Docker expects,
# as long as the dependencies are actually downloaded.
# Alternatively, a task that builds but doesn't run tests can be used:
# RUN gradle build --no-daemon --stacktrace -x test || gradle --stop

# --- Stage 2: Build Project ---
# Use the same Gradle image or a compatible one.
# FROM gradle:8.14.1-jdk${JAVA_VERSION}${JAVA_OS_VERSION} AS builder

# Set the working directory
# WORKDIR /app

# Copy downloaded dependencies from the 'deps' stage.
# This ensures we don't re-download them.
# COPY --from=deps /app /app
# Specifically, Gradle stores dependencies in /home/gradle/.gradle by default in the container.
# So, we copy the .gradle cache from the deps stage.
# COPY --from=deps /home/gradle/.gradle /home/gradle/.gradle

# Copy the rest of your application's source code
COPY src ./src

# Build the application, creating the executable JAR.
# Skip tests during the Docker build for faster builds; run tests in your CI pipeline.
RUN gradle bootJar --no-daemon --stacktrace -x test
# The `bootJar` task is specific to Spring Boot. If you're using a different task to build your JAR,
# replace `bootJar` with the appropriate task (e.g., `jar` or `assemble`).

# --- Stage 3: Run Application ---
# Use a lightweight base image with a Java Runtime Environment (JRE).
# Choose a JRE version compatible with your application.
# Eclipse Temurin is a good option for OpenJDK builds.
FROM amazoncorretto:${JAVA_VERSION}${JAVA_OS_VERSION}

# Set the working directory
WORKDIR /app

# Define an argument for the JAR file name/path.
# This makes it easier to manage if the JAR name changes.
ARG JAR_FILE_PATH="build/libs/*.jar"

# Copy the executable JAR from the 'builder' stage
COPY --from=builder /app/${JAR_FILE_PATH} application.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Set the entrypoint for the container.
# This command will run when the container starts.
# `java -jar application.jar` is the standard way to run a Spring Boot JAR.
# You can add JVM options here if needed, e.g., -Xmx512m
ENTRYPOINT ["java", "-jar", "application.jar"]

# Optional: Add a healthcheck (requires Spring Boot Actuator)
# HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
#   CMD curl -f http://localhost:8080/actuator/health || exit 1

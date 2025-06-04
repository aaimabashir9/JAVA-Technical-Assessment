import org.springframework.boot.gradle.tasks.aot.ProcessAot

plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("io.freefair.lombok") version "8.13.1"
	id("org.graalvm.buildtools.native") version "0.10.6"
}

group = "com.assessment"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.newrelic.telemetry:micrometer-registry-new-relic:0.10.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.postgresql:postgresql:42.7.6")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("jakarta.validation:jakarta.validation-api:3.1.1")
	implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<ProcessAot> {
    args("--spring.profiles.active=" + (project.properties["aotProfiles"] ?: "dev"))
}

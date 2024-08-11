import org.springframework.boot.gradle.tasks.aot.ProcessAot

plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("io.freefair.lombok") version "8.7.1"
	id("org.graalvm.buildtools.native") version "0.10.2"
}

group = "com.assessment"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	// version >= 42.2.x is required to support newer postgresql password encryption SCRAM-SHA-256
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.2")
	implementation("org.modelmapper:modelmapper:3.2.1")
	implementation("jakarta.validation:jakarta.validation-api:3.1.0")
	implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	testImplementation("junit:junit:4.13.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<ProcessAot> {
    args("--spring.profiles.active=" + (project.properties["aotProfiles"] ?: "dev"))
}

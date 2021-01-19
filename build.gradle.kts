plugins {
    java
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

group = "com.github.comrada.forwarder"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_15
}

repositories {
    jcenter()
}

configurations {
    all {
        exclude(module = "spring-boot-starter-logging")
    }
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.springframework.boot", "spring-boot-starter-log4j2")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml")

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}


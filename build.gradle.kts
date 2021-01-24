plugins {
    java
    id("org.springframework.boot") version "2.4.2"
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

val guavaVer = "30.1-jre"

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.springframework.boot", "spring-boot-starter-log4j2")
    implementation("org.springframework.boot", "spring-boot-starter-webflux")
    implementation("org.springframework.boot", "spring-boot-starter-oauth2-client")
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml")
    implementation("com.google.guava", "guava", guavaVer)

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}


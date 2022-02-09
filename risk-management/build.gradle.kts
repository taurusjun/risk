plugins {
    kotlin("jvm") version "1.6.10"
    java
    id("org.springframework.boot")
    application
}

group = "com.mtech.risk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":risk-dataio"))
    implementation(project(":risk-plugins"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass.set("com.mtech.risk.management.AppKt")
}
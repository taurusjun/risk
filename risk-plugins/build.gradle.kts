plugins {
    kotlin("jvm") version "1.6.10"
    java
}

group = "com.mtech.risk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":risk-data-platform"))
    implementation(project(":risk-dataio"))
    implementation(project(":risk-tools"))
    implementation(project(":risk-base"))

    implementation("org.mvel:mvel2")
    implementation("com.google.code.gson:gson")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
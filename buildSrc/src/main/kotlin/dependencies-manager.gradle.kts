// .gradle.kts前面的名字即插件名，引入此插件的项目都将应用此插件定义的所有配置

plugins {
    java
    `java-library`
    `maven-publish`
    id("io.freefair.lombok")
}

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/nexus/content/groups/public")
    maven("https://maven.aliyun.com/nexus/content/repositories/gradle-plugin")
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}


group = "com.mtech.risk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2")
    implementation("org.mvel:mvel2:2.4.14.Final")
// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

configurations {
    subprojects{
        apply(plugin = "dependencies-manager")
    }

    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}


tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
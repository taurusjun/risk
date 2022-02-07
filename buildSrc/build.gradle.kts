plugins {
    `kotlin-dsl`
}

project.version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/nexus/content/groups/public")
    maven("https://maven.aliyun.com/nexus/content/repositories/gradle-plugin")
    mavenCentral()
    gradlePluginPortal()
    maven("https://plugins.gradle.org/m2/")
}

dependencies{
    // 预编译插件所需的外部插件必须先在此引入 https://docs.gradle.org/current/userguide/custom_plugins.html#applying_external_plugins_in_precompiled_script_plugins
    implementation("io.freefair.gradle:lombok-plugin:6.1.0-m3")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.5.3")
}
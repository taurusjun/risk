plugins {
    id("org.springframework.boot")
}

dependencies{
    implementation(project(":api"))
    implementation(libs.spring.boot.web)
}
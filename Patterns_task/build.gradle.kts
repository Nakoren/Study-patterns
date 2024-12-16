plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation ("com.charleskorn.kaml:kaml:0.60.0")
    implementation ("org.postgresql:postgresql:42.2.20")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
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
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation ("com.charleskorn.kaml:kaml")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
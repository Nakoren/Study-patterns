plugins {
    kotlin("jvm") version "1.9.23"
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("com.charleskorn.kaml:kaml:0.60.0")
    implementation ("org.postgresql:postgresql:42.2.20")
    implementation ("org.openjfx:javafx:17.0.2")
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

kotlin {
    jvmToolchain(21)
}
plugins {
    kotlin("jvm") version "2.0.20"
}

group = "es.prog2425.taskmanager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotest (testeo con estilo BDD)
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")

    // MockK para mocking en Kotlin
    testImplementation("io.mockk:mockk:1.13.8")

    // Kotlin test (opcional si usas Kotest)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform() // Necesario para que Kotest funcione con JUnit 5
    testLogging {
        events("passed", "skipped", "failed") // Muestra los resultados de los tests en consola
    }
}

kotlin {
    jvmToolchain(21) // Usa Java 21
}

sourceSets {
    test {
        kotlin.srcDirs("src/test/kotlin")
    }
}

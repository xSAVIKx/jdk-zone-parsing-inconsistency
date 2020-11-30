import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("com.google.truth:truth:1.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events.add(TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}

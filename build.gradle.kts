plugins {
    kotlin("jvm") version "2.2.21"
    id("com.diffplug.spotless") version "8.1.0"
}

group = "org.francescfe"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktlint("1.2.1")
            .setEditorConfigPath("$rootDir/.editorconfig")

        target("**/*.kt")
        targetExclude("build/**", ".gradle/**")
    }

    kotlinGradle {
        ktlint("1.2.1")
        target("**/*.gradle.kts")
    }
}

plugins {
    kotlin("jvm") version "2.2.21"
    id("com.diffplug.spotless") version "8.1.0"
    application
}

group = "org.francescfe"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:6.0.7")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("org.francescfe.MainKt")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
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

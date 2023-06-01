
////////////////////////////////////////
// PLUGINS

/*
 * Order matters — plugins should come before configuration.
 * https://docs.gradle.org/current/samples/sample_publishing_java_libraries.html
 * https://docs.gradle.org/current/userguide/jacoco_plugin.html
 */
plugins {
    `java-library`  // Compiling into a library JAR
    `maven-publish` // Publishing to Artifactory
    // For downloading sources + docs:
    // https://stackoverflow.com/questions/28404149
    id("idea")
}

////////////////////////////////////////
// PROJECT INFO

// All modules should have their own name and description:
description = "Enhancing Guava's immutable collection"

// Usually modules have the same version and group:
// Also note that we're matching this lib's version to the corresponding Guava version.
allprojects {
    project.version = "19.0.1-SNAPSHOT"
    project.group = "com.terheyden.guava"
}

////////////////////////////////////////
// DEPENDENCIES

dependencies {

    api("com.google.guava:guava:19.0") {
        // https://mvnrepository.com/artifact/com.google.guava/guava
        because("Immutable collection")
    }

    compileOnly("com.github.spotbugs:spotbugs-annotations:4.+") {
        // https://mvnrepository.com/artifact/com.github.spotbugs/spotbugs-annotations
        because("Many useful annotations")
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.+") {
        // Use JUnit Jupiter for testing.
        // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
        because("Unit tests: API")
    }

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.+") {
        // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
        because("Unit tests: Implementation")
    }

    testImplementation("org.assertj:assertj-core:3.+") {
        // https://joel-costigliola.github.io/assertj/assertj-core-quick-start.html
        // https://mvnrepository.com/artifact/org.assertj/assertj-core
        because("Fluent assertions are awesome")
    }

    testImplementation("org.mockito:mockito-core:5.+") {
        // https://mvnrepository.com/artifact/org.mockito/mockito-core
        because("Mocking is awesome")
    }

    testImplementation("org.mockito:mockito-junit-jupiter:5.+") {
        // https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
        because("Adds @ExtendWith support")
    }
}

////////////////////////////////////////
// DEPENDENCY LOCKING

// How to lock everything:
//   1. Cut your release branch
//   2. Lock the root buildscript (or project): ./gradlew dependencies --write-locks
//   3. Lock sub-modules (if applicable): ./gradlew <modulename>:dependencies --write-locks
//   4. Commit the lock files, so all builds on the release branch use the same stable versions

// Enable locking for all dependency configurations:
// https://docs.gradle.org/current/userguide/dependency_locking.html
dependencyLocking {
    lockAllConfigurations()
}

// Cascade locking to subprojects:
subprojects {
    dependencyLocking {
        lockAllConfigurations()
    }
}

// Enable plugin locking:
// https://docs.gradle.org/current/userguide/dependency_locking.html
buildscript {
    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
    }
}

////////////////////////////////////////
// JAVADOCS AND SOURCES

// For some reason, downloading sources and docs for dependencies
// is IDE-specific in Gradle.
idea {
    module {
        // Download sources and docs for dependencies:
        // https://stackoverflow.com/questions/28404149
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    // Create JARs with sources and javadocs attached:
    // https://docs.gradle.org/current/userguide/java_plugin.html
    withJavadocJar()
    withSourcesJar()

    // Preferred way to specify Java version:
    // See: https://docs.gradle.org/current/userguide/java_plugin.html
    // Also: https://docs.gradle.org/current/userguide/toolchains.html
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

////////////////////////////////////////
// TASKS

tasks.test {
    // Hook in JUnit Jupiter for the 'test' task.
    useJUnitPlatform()
}

// Customize the Javadoc task:
tasks.javadoc {
    // If compatible, generate HTML5 output:
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

////////////////////////////////////////
// PUBLISHING AND REPO INFO

repositories {
    mavenCentral()
}

// ./gradlew publish = publish the lib
publishing {
    // Customize the generated POM file:
    publications {
        create<MavenPublication>(project.name) { // Create a publication...
            from(components["java"]) // We're going to publish a Java JAR and its metadata...
            pom {
                artifactId = project.name
                name.set("Enhancing Guava's immutable collection")
                description.set(project.description)
                url.set("https://github.com/terheyden/guava-fluent-immutables")
            }
        }
    }
}

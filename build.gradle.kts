plugins {
    java
    kotlin("jvm") version "1.7.21"
    id("maven-publish")
    id("java-library")
    id("org.jetbrains.dokka") version "1.7.20"
    id("com.avast.gradle.docker-compose") version ("0.16.11")
}


group = "it.testee"
version = "1.6.7"


repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    // selenium
    implementation("org.seleniumhq.selenium:selenium-java:4.6.0")
    // testng
    implementation("org.testng", "testng", "7.5")
    // reportng
    implementation("com.github.hibissscus:reportng:1.5.2")
}

tasks {
    test {
        description = "run e2e test locally"
        group = "verification"
        outputs.upToDateWhen { false }
        testLogging.showStandardStreams = true
        reports.html.required.set(false)
        reports.junitXml.required.set(false)
        useTestNG {
            suites("src/test/resources/local.xml")
            useDefaultListeners = false
            listeners = setOf("testee.it.reportng.HTMLReporter")
            systemProperties = mapOf(
                "testee.it.version" to "$version",
                "testee.it.reportng.title" to "testee-e2e",
                "testee.it.reportng.zip" to "true",
                "testee.it.reportng.slack" to "false",
                "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
                "testee.it.reportng.slack.channel" to "xxxx"
            )
        }
    }
}

tasks.register<Test>("docker") {
    description = "run entire e2e test suite"
    group = "verification"
    outputs.upToDateWhen { false }
    testLogging.showStandardStreams = true
    reports.html.required.set(false)
    reports.junitXml.required.set(false)
    useTestNG {
        suites("src/test/resources/docker.xml")
        useDefaultListeners = false
        listeners = setOf("testee.it.reportng.HTMLReporterRuntime", "testee.it.reportng.HTMLReporter")
        systemProperties = mapOf(
            "e2e.selenium" to System.getProperty("e2e.selenium", ""),
            "e2e.url" to System.getProperty("e2e.url", ""),
            "testee.it.version" to "$version",
            "testee.it.reportng.title" to "testee-e2e-docker",
            "testee.it.reportng.zip" to "true",
            "testee.it.reportng.slack" to "false",
            "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
            "testee.it.reportng.slack.channel" to "xxxx"
        )
    }
}

val dokkaJavadocJar by tasks.creating(Jar::class) {
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.get().outputDirectory.get())
    archiveClassifier.set("javadoc")
}

val sourcesJar by tasks.creating(Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENSE.txt") { into("META-INF") }
    from("README.md") { into("META-INF") }
}

tasks {
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(configurations.runtimeClasspath.get().filter {
            it.name.contains("reportng") || it.name.contains("velocity") || it.name.contains("spring-web") || it.name.contains("commons-collections") || it.name.contains(
                "commons-lang"
            ) || it.name.contains("gson") || it.name.contains("guice") || it.name.contains("inject") || it.name.contains("aopalliance")
        }.onEach { println("add from dependencies: ${it.name}") }.map { if (it.isDirectory) it else zipTree(it) })
        val sourcesMain = sourceSets.main.get()
        sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
        from(sourcesMain.output)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.name
            groupId = project.group.toString()
            version = project.version.toString()

            from(components["java"])
        }
    }
}

dockerCompose {
    useComposeFiles.add(
        if (org.gradle.internal.os.OperatingSystem.current() != null &&
            org.gradle.internal.os.OperatingSystem.current().toString().contains("aarch64")
        ) "docker-compose-arm.yml"
        else "docker-compose.yml"
    )
    isRequiredBy(tasks.getByName("docker"))
}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version ("1.5.30")
}

group = "testee"
version = "1.0.3"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
    // selenium
    implementation("org.seleniumhq.selenium:selenium-support:4.0.0-beta-4")
    implementation("org.seleniumhq.selenium:selenium-java:4.0.0-beta-4")
    // testng
    implementation("org.testng:testng:7.4.0")
    implementation("com.github.hibissscus:reportng:1.3.7")
}

// Customise the "compileKotlin" task.
val compileKotlin by tasks.getting(KotlinCompile::class) {
    kotlinOptions.jvmTarget = "1.8"
    doLast { println("Finished compiling Kotlin source code") }
}

// Customise the "compileTestKotlin" task.
val compileTestKotlin by tasks.getting(KotlinCompile::class) {
    kotlinOptions.jvmTarget = "1.8"
    doLast { println("Finished compiling Kotlin source code for testing") }
}

tasks {
    test {
        description = "run e2e test locally"
        group = "verification"
        useTestNG {
            useDefaultListeners = false
            listeners = setOf("testee.it.reportng.HTMLReporter")
            systemProperties = mapOf(
                "testee.it.reportng.title" to "testee-e2e",
                "testee.it.reportng.slack" to "false",
                "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
                "testee.it.reportng.slack.channel" to "xxxx"
            )
        }
    }
}

tasks.register<Test>("e2e") {
    description = "run entire e2e test suite"
    group = "verification"
    useTestNG {
        useDefaultListeners = false
        listeners = setOf("testee.it.reportng.HTMLReporter")
        suites("src/test/resources/e2e.xml")
        systemProperties = mapOf(
            "e2e.selenium" to System.getProperty("e2e.selenium", ""),
            "testee.it.reportng.title" to "testee-e2e",
            "testee.it.reportng.slack" to "false",
            "testee.it.reportng.slack.token" to "xxxx-xxxxxxxxxx-xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxx",
            "testee.it.reportng.slack.channel" to "xxxx"
        )
    }
}

tasks {
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(
            configurations.runtimeClasspath.get()
                .onEach { println("add from dependencies: ${it.name}") }
                .map { if (it.isDirectory) it else zipTree(it) })
        val sourcesMain = sourceSets.main.get()
        sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
        from(sourcesMain.output)
    }
}
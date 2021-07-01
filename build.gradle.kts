import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version ("1.5.20")
}

group = "testee"
version = "1.3.6"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.20")
    // selenium
    implementation("org.seleniumhq.selenium:selenium-support:4.0.0-beta-4")
    implementation("org.seleniumhq.selenium:selenium-java:4.0.0-beta-4")
    // testng
    implementation("org.testng:testng:7.1.0")
    // reportng (+ json-20180813.jar okhttp-2.7.5.jar retrofit-1.9.0.jar velocity-dep-1.4.jar)
    implementation("com.github.hibissscus:reportng:1.3.6")
    // templating
    implementation("org.apache.pdfbox:pdfbox:1.2.1")
    implementation("commons-io:commons-io:1.3.2")
    implementation("com.google.inject:guice:3.0")
    implementation("velocity:velocity-dep:1.4")
    // slack
    implementation("com.squareup.retrofit:retrofit:1.9.0")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("org.json:json:20180813")
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
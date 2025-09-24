plugins {
    kotlin("jvm") version "2.2.0"
    application
}

group = "algorithms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    maxHeapSize = "6g"
    maxParallelForks = 1
    jvmArgs("-XX:+UseG1GC", "-XX:MaxGCPauseMillis=200","-Xss4m")
    jvmArgs()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
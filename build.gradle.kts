plugins {
    kotlin("jvm") version "1.4.32"
}

group = "org.inego"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotest = "4.4.3"


dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotest")
    testImplementation("io.kotest:kotest-assertions-core:$kotest")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

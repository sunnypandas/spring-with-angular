plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
    application
}

group = "com.citi"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.citi.abbr.SpringWithAngularApplication"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ui"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.named("bootJar") {
    finalizedBy("deleteDist")
}

tasks.register<Delete>("deleteDist") {
    finalizedBy("prepareDist")
    if (file("dist").exists()) {
        delete(file("dist"))
    }
}

tasks.register("prepareDist") {
    finalizedBy("copyLibs")
    mkdir("dist")
}

tasks.register<Copy>("copyLibs") {
    finalizedBy("copyShells")
    from("build/libs")
    into("dist")
}

tasks.register<Copy>("copyShells") {
    from("bin")
    into("dist")
}
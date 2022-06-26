buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath "gradle.plugin.com.github.johnrengelman:shadow:7.1.2"
    }
}

plugins {
    id 'org.springframework.boot' version '2.7.1'
    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
    id 'com.github.johnrengelman.shadow' version '7.1.2'
    id 'java'
}

group = 'com.citi'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

jar {
    manifest {
        attributes "Main-Class": "com.citi.abbr.SpringWithAngularApplication"
    }

    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}

task deleteDist(type:Delete) {
    if (file('dist').exists()) {
        delete file('dist')
    }
}

task prepareDist {
    mustRunAfter build
    doLast {
        mkdir "dist"
    }
}

task copyLibs(type: Copy) {
    from 'build/libs'
    into 'dist'
}

task copyShells(type: Copy) {
    from 'bin'
    into 'dist'
}
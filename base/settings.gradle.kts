rootProject.name = "base"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.7.2"
        id("io.spring.dependency-management") version "1.0.12.RELEASE"
        kotlin("jvm") version "1.6.21"
        kotlin("plugin.spring") version "1.6.21"
        kotlin("plugin.jpa") version "1.6.21"
    }
}
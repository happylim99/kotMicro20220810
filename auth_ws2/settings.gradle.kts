rootProject.name = "auth_ws2"

include(":base")
project(":base").projectDir = File("../base")

include(":common_dto")
project(":common_dto").projectDir = File("../common_dto")

include(":auth")
project(":auth").projectDir = File("../auth")

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.7.2"
        id("io.spring.dependency-management") version "1.0.12.RELEASE"
        kotlin("jvm") version "1.6.21"
        kotlin("plugin.spring") version "1.6.21"
        kotlin("plugin.jpa") version "1.6.21"
    }
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyFirstApplication"
include(":app")
include(":ch06_view")
include(":ch07_layout")
include(":ch09_resource")
include(":ch08_event")
include(":ch10_dialog")

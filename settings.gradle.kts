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
        maven { url = java.net.URI("https://jitpack.io") }
    }
}

rootProject.name = "MyFirstApplication"
include(":app")
include(":ch06_view")
include(":ch07_layout")
include(":ch09_resource")
include(":ch08_event")
include(":ch10_dialog")
include(":ch11_jetpack")
include(":ch13_activity")
include(":mid_practice")
include(":ch17_storage")
include(":ch18_joyce")
include(":ch18_image")
include(":ch18_image")
include(":ch18_image:ch10_push")
include(":ch10_notification")
include(":graphapplication")

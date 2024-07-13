pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "TicketApp"
include(":app")
include(":data")
include(":core-ui")
include(":core")
include(":features")
include(":features:airline-tickets")
include(":features:hotels")
include(":features:in-short")
include(":features:subscriptions")
include(":features:profile")
include(":features:airline-tickets:domain")
include(":features:airline-tickets:presentation")
include(":features:hotels:presentation")
include(":features:in-short:presentation")
include(":features:profile:presentation")
include(":features:subscriptions:presentation")
include(":features:hotels:domain")
include(":features:in-short:domain")
include(":features:profile:domain")
include(":features:subscriptions:domain")

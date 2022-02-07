@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

rootProject.name = "risk"
include("core","api","app")
include("risk-dataio")
include("risk-management")

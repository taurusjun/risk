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
include("risk-dataio")
include("risk-management")
include("risk-plugins")
include("risk-tools")
include("risk-base")

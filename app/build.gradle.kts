import java.util.Properties

plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    id("architectcoders.android.application")
    id("architectcoders.android.application.compose")
    id("architectcoders.di.library.compose")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "io.devexpert.architectcoders"

    defaultConfig {
        applicationId = "io.devexpert.architectcoders"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "io.devexpert.architectcoders.di.HiltTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        //val properties = Properties()
        //properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

        //val tmdbApiKey = properties.getProperty("TMDB_API_KEY", "")
        //buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
        buildConfigField("String", "TMDB_API_KEY", "\"dummy\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(project(":domain:region"))
    implementation(project(":framework:core"))
    implementation(project(":framework:movie"))
    implementation(project(":framework:region"))
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.location)
    implementation(libs.firebase.analytics)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.androidx.room.ktx)
    kspAndroidTest(libs.androidx.room.compiler)
    androidTestImplementation(libs.okhttp.mockwebserver)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")

}

android {
    namespace = "com.florinda.store"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.florinda.store"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            // Enables code shrinking, obfuscation, and optimization for only
            isMinifyEnabled = true
            // Enables resource shrinking, which is performed by the
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"


    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation (libs.androidx.activity.ktx)

    implementation(project(path = ":domain"))
    implementation(project(path = ":di"))

    //firebase 
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)



    // lifecycle compose
    implementation(libs.androidx.lifecycle.runtime.compose)
    // material compose
    implementation(libs.material3)

    /** Lifecycle */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v282)

    /** Material icons */
    implementation(libs.androidx.material.icons.extended)

    /** Navigation */
    implementation(libs.androidx.navigation.compose.v275)

    /** Coil */
    implementation(libs.coil.compose)



    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.maps.compose)

    // Google Maps
    implementation(libs.play.services.maps)

    // Coroutine Lifecycle Scopes
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v262)
    implementation(libs.androidx.lifecycle.runtime.ktx.v282)

    //Lifecycle
    implementation(libs.androidx.runtime.livedata)

    // Paging Compose
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.androidx.paging.compose)

    //palette
    implementation(libs.androidx.palette.ktx)

    //timber
    implementation(libs.androidx.datastore.preferences)


}
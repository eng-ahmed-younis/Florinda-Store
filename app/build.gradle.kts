plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.kotlin.kapt)
   // id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")

}

android {
    namespace = "com.florinda.store"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.florinda.store"
        minSdk = 27
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


    implementation(project(path = ":domain"))
    implementation(project(path = ":di"))

    //firebase 
    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")




    // lifecycle compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.1")
    // material compose
    implementation("androidx.compose.material3:material3:1.2.1")

    /** Lifecycle */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    /** Material icons */
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    /** Navigation */
    val nav_version = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    /** Coil */
    implementation("io.coil-kt:coil-compose:2.5.0")



    // Compose dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.17.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("com.google.maps.android:maps-compose:1.0.0")

    // Google Maps
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Coroutine Lifecycle Scopes
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Lifecycle
    val compose_version = "1.5.4"
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")

    // Paging Compose
    implementation("com.google.accompanist:accompanist-pager:0.17.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.17.0")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    //palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    //timber
    implementation(libs.androidx.datastore.preferences)


}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // ❌ compose plugin hata sakta hai (optional)
    // alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.callingapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.callingapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // ❌ Compose disable kar (important)
    buildFeatures {
        compose = false
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)

    // 🔥 MOST IMPORTANT (XML support)
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Material UI (buttons, etc)
    implementation("com.google.android.material:material:1.11.0")

    // Optional (better layouts)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.activity)

    // Testing (rehne de)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
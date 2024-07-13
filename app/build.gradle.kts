plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.ticketapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ticketapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":features:airline-tickets:presentation"))
    implementation(project(":features:hotels:presentation"))
    implementation(project(":features:in-short:presentation"))
    implementation(project(":features:subscriptions:presentation"))
    implementation(project(":features:profile:presentation"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.android)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    implementation(libs.ui.splashscreen)
    implementation(libs.ui.viewBindingDelegate)
    //Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.androidx.junit)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.lucas.restaurantlist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.lucas.restaurantlist"
        minSdk = 28
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
    buildFeatures {
        compose = true
        dataBinding = true
    }

    configurations {
        all {
            exclude(group = "com.intellij", module = "annotations")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.coordinatorlayout)
    implementation(libs.material)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.adapter.rxjava2)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.okhttp.urlconnection)
    implementation(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.room)
    ksp(libs.room.room.compiler)
    implementation(libs.room.room.ktx)

    // Glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // Coroutines
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    // Koin
    implementation(libs.koin)
    implementation(libs.koin.android)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.swiperefreshlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
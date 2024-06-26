plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.btl_mobile_spotify"
    compileSdk = 33
    sourceSets {
        getByName("main") {
            jniLibs.srcDir("libs")
        }
    }


    defaultConfig {
        applicationId = "com.example.btl_mobile_spotify"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
//    kotlin {
//        jvmToolchain(8)
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation(files("libs/acrcloud-universal-sdk-1.3.24.jar"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // compose navigation
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //coil
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("com.facebook.android:facebook-login:latest.release")
    implementation("com.facebook.android:facebook-android-sdk:latest.release")

    //glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    //hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //exo player
    val exoplayer_version = "2.16.1"
    implementation("com.google.android.exoplayer:exoplayer-core:$exoplayer_version")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoplayer_version")
    implementation("com.google.android.exoplayer:exoplayer-dash:$exoplayer_version")
    implementation("com.google.android.exoplayer:exoplayer-smoothstreaming:$exoplayer_version")
    implementation("com.google.android.exoplayer:extension-mediasession:$exoplayer_version")

    //room
    val room_version = "2.5.1"
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
//    kapt("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    //retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.1")

    //constrain layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Local Unit Tests
    implementation("androidx.test:core:1.2.0")
    testImplementation("junit:junit:4.13")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1")
    testImplementation("com.google.truth:truth:1.0.1")
    testImplementation("org.mockito:mockito-core:2.21.0")

    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13")
    androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:2.12.1")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("com.google.truth:truth:1.0.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("org.mockito:mockito-core:2.21.0")

}
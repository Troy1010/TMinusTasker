plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.tminus1010.tminustasker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tminus1010.tminustasker"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

var tmCommonKotlinVersion = "1.8.3-alpha02"

// Jitpack does not play nicely with libraries that depend on each other in the same project. This is a workaround.
configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(
            module("com.github.Troy1010:tmcommonkotlin-androidx:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-androidx:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-core:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-core:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-coroutines:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-coroutines:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-customviews:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-customviews:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-dsl:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-dsl:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-microphone:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-microphone:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-misc:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-misc:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-rx3:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-rx3:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-tuple:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-tuple:${tmCommonKotlinVersion}"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-view:${tmCommonKotlinVersion}")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-view:${tmCommonKotlinVersion}"))
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-rc01")

    // # Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")

    // # DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // # Inject
    implementation("javax.inject:javax.inject:1") // This is not necessary, but makes it easier to quick-fix the import for @Inject.

    // # Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // # TMCommonKotlin
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-androidx:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-core:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-coroutines:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-customviews:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-misc:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-tuple:${tmCommonKotlinVersion}")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-view:${tmCommonKotlinVersion}")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
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

// Jitpack does not play nicely with libraries that depend on each other in the same project. This is a workaround.
configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(
            module("com.github.Troy1010:tmcommonkotlin-androidx:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-androidx:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-core:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-core:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-coroutines:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-coroutines:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-customviews:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-customviews:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-dsl:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-dsl:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-microphone:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-microphone:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-misc:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-misc:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-rx3:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-rx3:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-tuple:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-tuple:1.8.3"))

        substitute(
            module("com.github.Troy1010:tmcommonkotlin-view:1.8.3")
        ).using(module("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-view:1.8.3"))
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // # TMCommonKotlin
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-androidx:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-core:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-coroutines:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-customviews:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-misc:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-tuple:1.8.3")
    implementation("com.github.Troy1010.TMCommonKotlin:tmcommonkotlin-view:1.8.3")
}
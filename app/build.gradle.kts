import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.eslam.vehicletracker"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    implementation(Libs.appcompat)
    implementation(Libs.core_ktx)
    implementation(Libs.constraintlayout)

    implementation(Libs.android_arch_lifecycle_extensions)
    implementation(Libs.viewmodel)

    implementation(Libs.retrofit)
    implementation(Libs.converter_gson)
    implementation(Libs.logging_interceptor)
    implementation(Libs.adapter_rxjava2)

    implementation(Libs.rxandroid)
    implementation(Libs.rxkotlin)

    implementation(Libs.dagger)
    annotationProcessor(Libs.dagger_compiler)
    kapt(Libs.dagger_compiler)

    implementation(Libs.lifecycle_viewmodel_ktx)

    implementation(Libs.play_services_maps)

    implementation(Libs.airlocation)

    implementation(Libs.material)

    testImplementation(Libs.junit)
    testImplementation(Libs.mockito_core)
    testImplementation(Libs.core_testing)
    testImplementation(Libs.mockito_kotlin)
    testImplementation(Libs.rx2)

    androidTestImplementation(Libs.androidx_test_runner)
}
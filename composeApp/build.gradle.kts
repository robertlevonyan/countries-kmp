@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.js.translate.intrinsic.operation.binaryIntrinsic

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.skie)
    alias(libs.plugins.compose.compiler)
}

kotlin {
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(project.projectDir.path)
//                    }
//                }
//            }
//        }
//        binaries.executable()
//    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.loggingjvm)
            implementation(libs.koin.android)
            implementation(libs.sqldelight.driver.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.serialization)
            implementation(libs.coil)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            implementation(libs.lottie)
            implementation(libs.material3.windowsizeclass)
            implementation(libs.precompose)
            implementation(libs.precompose.viewmodel)
            implementation(libs.precompose.koin)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.test)
            implementation(libs.stately.common)
            implementation(libs.stately.concurrent.collections)
            implementation(libs.sqldelight.coroutines.extensions)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.loggingjvm)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqldelight.driver.jvm)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.driver.native)
        }
    }

//    project.extensions.findByType(KotlinMultiplatformExtension::class.java)?.apply {
//        targets
//            .filterIsInstance<KotlinNativeTarget>()
//            .flatMap { it.binaries }
//            .forEach { compilationUnit -> compilationUnit.linkerOpts("-lsqlite3") }
//    }

//    macosArm64("desktop") {
//        binaries { executable() }
//    }
}

android {
    namespace = "com.robertlevon.countrieskmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.robertlevon.countrieskmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "com.robertlevon.countrieskmp"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(File("../desktopIcon", "app-icon-1024.png"))
                appStore = true
                bundleID = "com.robertlevon.countrieskmp"
            }
            outputBaseDir.set(getLayout().buildDirectory.dir("outputs"))
        }
    }
}

//compose.experimental {
//    web.application {}
//}

task("testClasses")

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.robertlevon.countrieskmp")
            generateAsync.set(true)
        }
    }
}

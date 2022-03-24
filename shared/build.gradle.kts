plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val ktorVersion = "1.5.0"
        val coroutineVersion = "1.4.2"

        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.7.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
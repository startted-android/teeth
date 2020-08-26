package plugin

import Libs
import Versions
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.implementation

internal val Project.appExtension: AppExtension
    get() = extensions.getByType()

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

open class BuildPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        addCommonPlugins()
                        addAndroidLibrarySection()
                        addCommonDependencies()
                    }
                    is AppPlugin -> {
                        addCommonPlugins()
                        addCommonDependencies()
                        addAndroidApplicationSection()
                    }
                    is KotlinBasePluginWrapper -> {
                        tasks.withType(KotlinCompile::class.java).configureEach {
                            kotlinOptions {
                                jvmTarget = "1.8"
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Project.addAndroidApplicationSection() = appExtension.run {
    defaultConfig {
        applicationId = "com.startted-android.teeth"
        compileSdkVersion(Versions.Project.compileSdkVersion)
        minSdkVersion(Versions.Project.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun Project.addAndroidLibrarySection() = libraryExtension.run {
    defaultConfig {
        minSdkVersion(Versions.Project.minSdkVersion)
        compileSdkVersion(Versions.Project.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun Project.addCommonDependencies() = dependencies {
    implementation(Libs.stdlib)
}

private fun Project.addCommonPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    plugins.apply("kotlin-android-extensions")
}
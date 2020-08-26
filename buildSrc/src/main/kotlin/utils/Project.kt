package utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.withLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
        }
    }
}

fun Project.withKapts(vararg libs: String) {
    dependencies {
        libs.forEach {
            kapt(it)
        }
    }
}

fun Project.withApis(vararg libs: String) {
    dependencies {
        libs.forEach {
            api(it)
        }
    }
}

fun Project.withProjects(vararg projects: String) {
    dependencies {
        projects.forEach {
            implementation(project(it))
        }
    }
}
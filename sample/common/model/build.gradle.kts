apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion deps.android.build.compileSdkVersion
    buildToolsVersion deps.android.build.buildToolsVersion

    defaultConfig {
        minSdkVersion deps.android.build.minSdkVersion
        targetSdkVersion deps.android.build.targetSdkVersion
    }
}

dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation deps.stdlib
}
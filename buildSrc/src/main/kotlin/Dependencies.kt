import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion

object Libs {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    object AndroidX {

        object Ktx {
            const val coreKtx = "androidx.core:core-ktx:1.3.1"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Libraries.lifecycle}"
        }

        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val material = "com.google.android.material:material:1.3.0-alpha02"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Libraries.lifecycle}"
    }

    const val toothpickRuntime = "com.github.stephanenicolas.toothpick:toothpick-runtime:${Versions.Libraries.toothpick}"
    const val toothpickCompiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.Libraries.toothpick}"

    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

    object Network {
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Libraries.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Libraries.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Libraries.retrofit}"
        const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Libraries.retrofit}"
    }

    const val cicerone = "ru.terrakok.cicerone:cicerone:5.1.1"

}

object Plugins {
    const val gradleToolsPlugin = "com.android.tools.build:gradle:${Versions.Classpath.gradleTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Versions {

    const val kotlin = "1.4.0"

    object Libraries {
        const val lifecycle = "2.2.0"
        const val toothpick = "3.1.0"
        const val okHttp = "4.7.2"
        const val retrofit = "2.8.2"
    }

    object Project {
        const val compileSdkVersion = 30
        const val minSdkVersion = 23
    }

    object Classpath {
        const val gradleTools = "4.0.1"
    }

}
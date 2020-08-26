plugins {
    id("com.android.library")
    id("BuildPlugin")
}

dependencies {
    implementation(project(Modules.Sample.Common.model))

    implementation(Libs.toothpickRuntime)
    kapt(Libs.toothpickCompiler)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofitGson)
    implementation(Libs.Network.okhttpLoggingInterceptor)
    implementation(Libs.Network.retrofitRx)
}

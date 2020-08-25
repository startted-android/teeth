package com.startted.core_network.di

import com.startted.core_network.data.ApiClient
import com.startted.core_network.di.providers.ApiServiceProvider
import com.startted.core_network.di.providers.OkHttpClientProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule : Module() {
    init {
        bind(Gson::class.java).toInstance(Gson())
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingleton()
        bind(ApiClient::class.java).toProvider(ApiServiceProvider::class.java).providesSingleton()
    }
}
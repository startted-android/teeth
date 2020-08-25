package com.airatlovesmusic.core_network.di

import com.airatlovesmusic.core_network.data.ApiClient
import com.airatlovesmusic.core_network.di.providers.ApiServiceProvider
import com.airatlovesmusic.core_network.di.providers.OkHttpClientProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule : Module() {
    init {
        bind(Gson::class.java).toInstance(Gson())
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).singleton()
        bind(ApiClient::class.java).toProvider(ApiServiceProvider::class.java).singleton()
    }
}
package com.airatlovesmusic.core_network.di.providers

import com.airatlovesmusic.core_network.data.ApiClient
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiServiceProvider @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) : Provider<ApiClient> {

    override fun get(): ApiClient =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .baseUrl("https://raw.githubusercontent.com/airatlovesmusic/")
            .build()
            .create(ApiClient::class.java)
}
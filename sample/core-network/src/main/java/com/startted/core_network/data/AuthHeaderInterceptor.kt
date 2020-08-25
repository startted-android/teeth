package com.startted.core_network.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Used for header token injection
class AuthHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
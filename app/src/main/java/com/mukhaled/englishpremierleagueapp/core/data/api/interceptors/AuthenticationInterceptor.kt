package com.mukhaled.englishpremierleagueapp.core.data.api.interceptors

import com.mukhaled.englishpremierleagueapp.BuildConfig
import com.mukhaled.englishpremierleagueapp.core.data.api.ApiParameters
import com.squareup.moshi.Moshi
import okhttp3.*
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest: Request = chain.createAuthenticatedRequest()
        return chain.proceed(interceptedRequest)
    }

    private fun Interceptor.Chain.createAuthenticatedRequest(): Request {
        return request()
            .newBuilder()
            .addHeader(ApiParameters.AUTH_HEADER, BuildConfig.AUTH_KEY)
            .addHeader(ApiParameters.ACCEPT, "application/json")
            .build()
    }

}

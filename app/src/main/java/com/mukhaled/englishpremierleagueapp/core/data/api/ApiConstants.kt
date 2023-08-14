package com.mukhaled.englishpremierleagueapp.core.data.api

import com.mukhaled.englishpremierleagueapp.BuildConfig


object ApiConstants{
    const val BASE_ENDPOINT = BuildConfig.BASE_URL
    const val MATCHES: String = "competitions/2021/matches"
}

object ApiParameters {
    const val AUTH_HEADER = "X-Auth-Token"
    const val ACCEPT = "Accept"
}
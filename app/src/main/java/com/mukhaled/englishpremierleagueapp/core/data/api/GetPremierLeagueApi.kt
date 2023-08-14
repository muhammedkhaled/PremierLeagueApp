package com.mukhaled.englishpremierleagueapp.core.data.api

import com.mukhaled.englishpremierleagueapp.core.data.api.model.ApiPremierLeague
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetPremierLeagueApi {

    @Headers("${ApiParameters.ACCEPT}: application/json")
    @GET(ApiConstants.MATCHES)
    suspend fun getPremier(): ApiPremierLeague
}
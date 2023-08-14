package com.mukhaled.englishpremierleagueapp.core.domain.repositories

import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import io.reactivex.Flowable


interface HomeRepositoryInterface {
    fun getPremierLeague(): Flowable<List<ItemData>>
    suspend fun requestPremierLeague(): PremierLeague
    suspend fun storePremierLeague(list: List<ItemData>)
}
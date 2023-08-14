package com.mukhaled.englishpremierleagueapp.premierLeagueMatches.domain.useCase

import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import com.mukhaled.englishpremierleagueapp.core.domain.repositories.HomeRepositoryInterface
import javax.inject.Inject


class GetData @Inject constructor(private val homeRepository: HomeRepositoryInterface){
    operator fun invoke() = homeRepository.getPremierLeague()
}
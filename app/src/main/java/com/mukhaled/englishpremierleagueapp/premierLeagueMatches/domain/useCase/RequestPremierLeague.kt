package com.mukhaled.englishpremierleagueapp.premierLeagueMatches.domain.useCase

import com.mukhaled.englishpremierleagueapp.core.domain.repositories.HomeRepositoryInterface
import com.mukhaled.englishpremierleagueapp.core.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestPremierLeague @Inject constructor(
    private val homeRepository: HomeRepositoryInterface,
    private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke() {
        withContext(dispatchersProvider.io()){
            val premierLeague = homeRepository.requestPremierLeague()
            homeRepository.storePremierLeague(premierLeague.matches)
        }
    }
}
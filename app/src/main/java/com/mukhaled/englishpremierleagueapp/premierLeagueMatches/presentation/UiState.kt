package com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation

import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import com.mukhaled.englishpremierleagueapp.core.domain.model.Event

data class UiState(
    val isLoading: Boolean = false,
    val premierLeague: PremierLeague = PremierLeague("", emptyList()),
    val failure: Event<Throwable>? = null,
)
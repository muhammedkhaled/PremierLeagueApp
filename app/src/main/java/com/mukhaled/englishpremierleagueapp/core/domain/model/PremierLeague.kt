package com.mukhaled.englishpremierleagueapp.core.domain.model



data class PremierLeague(
    val competitionName: String,
    val matches: List<ItemData>
)

data class ItemData(
    val id: Int,
    val teamHome: String,
    val teamAway: String,
    val matchState: String,
    val homeScore: String,
    val awayScore: String,
    val time: String,
    val day: String,
    val date: String
)
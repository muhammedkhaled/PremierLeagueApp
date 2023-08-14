package com.mukhaled.englishpremierleagueapp.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPremierLeague(
    @field:Json(name = "competition") val competition: Competition?,
    @field:Json(name = "matches") val matches: List<Matche?>?
)

@JsonClass(generateAdapter = true)
data class Competition(
    @field:Json(name = "area") val area: Area?,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?
)


@JsonClass(generateAdapter = true)
data class Matche(
    @field:Json(name = "awayTeam")  val awayTeam: Team?,
    @field:Json(name = "homeTeam")  val homeTeam: Team?,
    @field:Json(name = "id")  val id: Int?,
    @field:Json(name = "matchday")  val matchday: Int?,
    @field:Json(name = "score")  val score: Score?,
    @field:Json(name = "stage")  val stage: String?,
    @field:Json(name = "status")  val status: String?,
    @field:Json(name = "utcDate")  val utcDate: String?
)

@JsonClass(generateAdapter = true)
data class Team(
    @field:Json(name = "id")  val id: Int?,
    @field:Json(name = "name")  val name: String?
)

@JsonClass(generateAdapter = true)
data class Area(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?
)

@JsonClass(generateAdapter = true)
data class FullTime(
    @field:Json(name = "awayTeam") val awayTeam: Int?,
    @field:Json(name = "homeTeam") val homeTeam: Int?
)

@JsonClass(generateAdapter = true)
data class Score(
    @field:Json(name = "duration") val duration: String?,
    @field:Json(name = "fullTime") val fullTime: FullTime?,
    @field:Json(name = "winner") val winner: String?
)
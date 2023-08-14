package com.mukhaled.englishpremierleagueapp.core.data.api.model.mappers

import com.mukhaled.englishpremierleagueapp.core.data.api.model.ApiPremierLeague
import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import java.text.SimpleDateFormat
import java.util.TimeZone
import javax.inject.Inject

class PremierLeagueMapper @Inject constructor() : ApiMapper<ApiPremierLeague, PremierLeague> {
    override fun mapToDomain(apiEntity: ApiPremierLeague): PremierLeague {
        return PremierLeague(
            competitionName = apiEntity.competition?.name.orEmpty(),
            matches = apiEntity.matches?.map {
                val homeScore = it?.score?.fullTime?.homeTeam ?: -1
                val awayScore =it?.score?.fullTime?.awayTeam ?: -1
                ItemData(
                    id = it?.id ?: -1,
                    teamHome = it?.homeTeam?.name.orEmpty(),
                    teamAway = it?.awayTeam?.name.orEmpty(),
                    matchState = it?.status.orEmpty(),
                    homeScore = if (homeScore == -1) "" else homeScore.toString(),
                    awayScore = if (awayScore == -1) "" else homeScore.toString(),
                    // todo format to time and date
                    time = convertUtc(it?.utcDate.orEmpty(), "hh:mm a"),
                    day = convertUtc(it?.utcDate.orEmpty(), "EEEE"),
                    date = convertUtc(it?.utcDate.orEmpty(), "dd MMM, yyyy"),
                )
            }.orEmpty()
        )
    }

    private fun convertUtc(utcDate: String, pattern: String): String {
        return try {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(utcDate)
            val dateFormatter = SimpleDateFormat(pattern) //this format changeable
            dateFormatter.timeZone = TimeZone.getDefault()
            dateFormatter.format(value)
            //Log.d("ourDate", ourDate);
        } catch (e: Exception) {
            "00-00-0000 00:00"
        }
    }
}





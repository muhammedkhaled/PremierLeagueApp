package com.mukhaled.englishpremierleagueapp.core.data.cash.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData


@Entity(tableName = "PremierLeague")
data class CashedItemData(
    @PrimaryKey
    val id: Int,
    val teamHome: String,
    val teamAway: String,
    val matchState: String,
    val homeScore: String,
    val awayScore: String,
    val time: String,
    val day: String,
    val date: String
) {
    companion object{
        fun fromDomain(itemData: ItemData): CashedItemData {
            return CashedItemData(
                id = itemData.id,
                teamHome = itemData.teamHome,
                teamAway = itemData.teamAway,
                matchState = itemData.matchState,
                homeScore = itemData.homeScore,
                awayScore = itemData.awayScore,
                time = itemData.time,
                day = itemData.day,
                date = itemData.date
            )
        }
    }

    fun toDomain(cashedItemData: CashedItemData): ItemData{
        return ItemData(
            id = cashedItemData.id,
            teamHome = cashedItemData.teamHome,
            teamAway = cashedItemData.teamAway,
            matchState = cashedItemData.matchState,
            homeScore = cashedItemData.homeScore,
            awayScore = cashedItemData.awayScore,
            time = cashedItemData.time,
            day = cashedItemData.day,
            date = cashedItemData.date
        )
    }
}
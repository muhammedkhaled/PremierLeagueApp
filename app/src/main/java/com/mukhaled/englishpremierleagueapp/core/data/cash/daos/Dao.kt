package com.realworld.android.petsave.common.data.cache.daos

import androidx.room.*
import androidx.room.Dao
import com.mukhaled.englishpremierleagueapp.core.data.cash.model.CashedItemData
import io.reactivex.Flowable

@Dao
abstract class Dao {

    @Transaction
    @Query("SELECT * FROM PremierLeague")
    abstract fun getAllMatches(): Flowable<List<CashedItemData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertItem(
        item: CashedItemData,
    )

    suspend fun insertItems(items: List<CashedItemData>) {
        for (cashedItem in items) {
            insertItem(cashedItem)
        }
    }

}

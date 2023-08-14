package com.mukhaled.englishpremierleagueapp.core.data.cash

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mukhaled.englishpremierleagueapp.core.data.cash.model.CashedItemData
import com.realworld.android.petsave.common.data.cache.daos.Dao

@Database(
    entities = [
      CashedItemData::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun matchesDao(): Dao
}

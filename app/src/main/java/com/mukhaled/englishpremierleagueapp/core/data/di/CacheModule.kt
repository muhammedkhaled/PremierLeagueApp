package com.mukhaled.englishpremierleagueapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.mukhaled.englishpremierleagueapp.core.data.cash.AppDatabase
import com.mukhaled.englishpremierleagueapp.core.data.cash.Cache
import com.mukhaled.englishpremierleagueapp.core.data.cash.RoomCache
import com.realworld.android.petsave.common.data.cache.daos.Dao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

  @Binds
  abstract fun bindCache(cache: RoomCache): Cache

  companion object {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
      return Room.databaseBuilder(
          context,
          AppDatabase::class.java,
          "premierLeagueMatches.db"
      )
          .build()
    }

    @Provides
    fun provideDao(
        petSaveDatabase: AppDatabase
    ): Dao = petSaveDatabase.matchesDao()

  }
}

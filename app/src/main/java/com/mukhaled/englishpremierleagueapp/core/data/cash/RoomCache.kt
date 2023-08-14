package com.mukhaled.englishpremierleagueapp.core.data.cash

import javax.inject.Inject

import com.mukhaled.englishpremierleagueapp.core.data.cash.model.CashedItemData
import com.realworld.android.petsave.common.data.cache.daos.Dao
import io.reactivex.Flowable

class RoomCache @Inject constructor(
    private val dao: Dao,
) : Cache {

  override suspend fun storeMatches(animals: List<CashedItemData>) {
    dao.insertItems(animals)
  }

  override fun getAllMatches(): Flowable<List<CashedItemData>> {
    return dao.getAllMatches()
  }

}

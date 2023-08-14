package com.mukhaled.englishpremierleagueapp.core.data.cash

import com.mukhaled.englishpremierleagueapp.core.data.cash.model.CashedItemData
import io.reactivex.Flowable


interface Cache {
  suspend fun storeMatches(animals: List<CashedItemData>)
  fun getAllMatches(): Flowable<List<CashedItemData>>
}

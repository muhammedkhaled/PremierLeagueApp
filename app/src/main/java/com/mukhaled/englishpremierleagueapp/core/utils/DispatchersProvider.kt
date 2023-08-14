package com.mukhaled.englishpremierleagueapp.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
  fun io(): CoroutineDispatcher = Dispatchers.IO
}

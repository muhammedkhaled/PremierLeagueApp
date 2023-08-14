package com.mukhaled.englishpremierleagueapp

import android.app.Application
import com.mukhaled.englishpremierleagueapp.core.utils.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

  override fun onCreate() {
    super.onCreate()
    initLogger()
  }

  private fun initLogger() {
    Logger.init()
  }
}

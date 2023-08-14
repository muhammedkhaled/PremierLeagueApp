package com.mukhaled.englishpremierleagueapp.core.utils

import timber.log.Timber


object Logger {
    private val logger by lazy {
        TimberLogging()
    }

    fun init() {
        Timber.plant(logger)
    }

    fun d(message: String, t: Throwable? = null) = logger.d(t, message)

    fun i(message: String, t: Throwable? = null) {
        logger.i(t, message)
    }

    fun e(t: Throwable? = null, message: String) = logger.e(t, message)

    fun wtf(t: Throwable? = null, message: String) = logger.wtf(t, message)
}


class TimberLogging: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}

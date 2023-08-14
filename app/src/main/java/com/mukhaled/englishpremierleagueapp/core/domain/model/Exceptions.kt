package com.mukhaled.englishpremierleagueapp.core.domain.model

import java.io.IOException


class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String, val code: Int? = null): Exception(message)

class UnauthorizedException(message: String, val code: Int? = null): Exception(message)

class LocationException(message: String): Exception()

class GPSException(message: String): Exception()

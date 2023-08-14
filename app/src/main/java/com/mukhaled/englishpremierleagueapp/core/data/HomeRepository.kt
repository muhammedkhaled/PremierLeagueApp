package com.mukhaled.englishpremierleagueapp.core.data

import com.mukhaled.englishpremierleagueapp.core.data.api.GetPremierLeagueApi
import com.mukhaled.englishpremierleagueapp.core.data.api.model.mappers.PremierLeagueMapper
import com.mukhaled.englishpremierleagueapp.core.data.cash.Cache
import com.mukhaled.englishpremierleagueapp.core.data.cash.model.CashedItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.NetworkException
import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import com.mukhaled.englishpremierleagueapp.core.domain.repositories.HomeRepositoryInterface
import io.reactivex.Flowable
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val getPremierLeagueApi: GetPremierLeagueApi,
    private val mapper: PremierLeagueMapper,
    private val cache: Cache,
) : HomeRepositoryInterface {

    override fun getPremierLeague(): Flowable<List<ItemData>> {
        return cache.getAllMatches()
            .distinctUntilChanged()
            .map { matchList ->
                matchList.map {
                    it.toDomain(it)
                }
            }
    }

    override suspend fun requestPremierLeague(): PremierLeague {
        try {
            return mapper.mapToDomain(getPremierLeagueApi.getPremier())
        } catch (e: HttpException) {
            throw handleException(e)
        }
    }

    override suspend fun storePremierLeague(list: List<ItemData>) {
        val matches = list.map {
            CashedItemData.fromDomain(it)
        }
        cache.storeMatches(matches)
    }

    private fun handleException(exception: HttpException): Exception =
    // TODO: get error schema and parse error
        // val errorResponse = convertErrorBody(exception)
        throw NetworkException(
            message = exception.message ?: "Code ${exception.code()}", code = exception.code()
        )
}


//    private fun convertErrorBody(exception: HttpException): ApiMessage? {
//        return try {
//            exception.response()?.errorBody()?.source()?.let {
//                val moshiAdapter = Moshi.Builder().build().adapter(ApiMessage::class.java)
//                moshiAdapter.fromJson(it)
//            }
//        } catch (exception: Exception) {
//            null
//        }
//    }


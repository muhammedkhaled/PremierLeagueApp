package com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukhaled.englishpremierleagueapp.core.domain.model.Event
import com.mukhaled.englishpremierleagueapp.core.domain.model.ItemData
import com.mukhaled.englishpremierleagueapp.core.domain.model.NetworkException
import com.mukhaled.englishpremierleagueapp.core.domain.model.PremierLeague
import com.mukhaled.englishpremierleagueapp.core.utils.Logger
import com.mukhaled.englishpremierleagueapp.core.utils.createExceptionHandler
import com.mukhaled.englishpremierleagueapp.premierLeagueMatches.domain.useCase.GetData
import com.mukhaled.englishpremierleagueapp.premierLeagueMatches.domain.useCase.RequestPremierLeague
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getData: GetData,
    private val requestPremierLeague: RequestPremierLeague,
    private val compositeDisposable: CompositeDisposable,
) : ViewModel() {

    init {
        subscribeToUpdates()
    }

    // uiState
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    // onEvent
    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.GetInitialData -> {
                getInitialData()
            }
        }
    }

    private fun subscribeToUpdates() {
        getData()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onNewList(it) },
                { onFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun onNewList(list: List<ItemData>) {
        Logger.d("Got more matches!")

        val updatedSet = list.toSet()
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                premierLeague = PremierLeague("Premier League", updatedSet.toList())
            )
        }
    }

    private fun getInitialData() {
        if (state.value.premierLeague.matches.isEmpty()) {
            loadFromApi()
        }
    }

    private fun loadFromApi() {
        _state.update { oldState ->
            oldState.copy(isLoading = true)
        }
        val errorMessage = "Failed to load Premier League"
        val exceptionHandler =
            viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            requestPremierLeague()
            _state.update { oldState ->
                oldState.copy(isLoading = false)
            }
        }
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException -> _state.update { oldState ->
                oldState.copy(
                    isLoading = false,
                    failure = Event(failure)
                )
            }
        }
    }
}
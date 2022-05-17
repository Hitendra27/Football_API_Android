package com.example.football_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.football_api.network.FootballTeamRepository
import com.example.football_api.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class FootballTeamViewModel (private val footballTeamRepository: FootballTeamRepository,
                             private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
                             private val coroutineScope: CoroutineScope = CoroutineScope(
                                 SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel() {

    private val _footballTeamLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val footballTeamLiveData: LiveData<UIState> get() = _footballTeamLiveData

    fun subscribeToFootballTeamInfo() {
        _footballTeamLiveData.postValue(UIState.LOADING())

        collectFootballTeamInfo()

        launch {
            footballTeamRepository.getActiveAlerts()
        }
    }

    private fun collectFootballTeamInfo() {
        launch {
            footballTeamRepository.responseFlow.collect { uiState ->
                when(uiState) {
                    is UIState.LOADING -> { _footballTeamLiveData.postValue(uiState) }
                    is UIState.SUCCESS -> { _footballTeamLiveData.postValue(uiState) }
                    is UIState.ERROR -> { _footballTeamLiveData.postValue(uiState) }
                }
            }
        }
    }
}
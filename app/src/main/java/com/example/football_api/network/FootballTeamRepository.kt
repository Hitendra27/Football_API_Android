package com.example.football_api.network

import com.example.football_api.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface FootballTeamRepository {
    val responseFlow: StateFlow<UIState>
    suspend fun getActiveAlerts()
}

class FootballTeamRepositoryImpl(
    private val footballAPI: FootballAPI
) : FootballTeamRepository {

    private val _responseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val responseFlow: StateFlow<UIState>
        get() = _responseFlow

    override suspend fun getActiveAlerts() {
        try {
            val response = footballAPI.getFootballTeams()

            if (response.isSuccessful) {
                response.body()?.let {
                    _responseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _responseFlow.value =
                        UIState.ERROR(IllegalStateException("Cards are coming as null!"))
                }

            } else {
                _responseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _responseFlow.value = UIState.ERROR(e)
        }
    }
}
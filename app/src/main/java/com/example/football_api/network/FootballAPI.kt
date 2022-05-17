package com.example.football_api.network

import com.example.football_api.model.FootballTeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballAPI {

    @GET(ACTIVE_ALERTS)
    suspend fun getFootballTeams(
        @Query("status")status:String="actual",
        @Query("message_type")type: String="alert"
    ): Response<FootballTeamResponse>

    companion object {
        const val BASE_URL = "https://api-football-standings.azharimm.site/leagues/eng.1/"
        private const val ACTIVE_ALERTS = "standings?season=2020&sort=asc"
    }
}
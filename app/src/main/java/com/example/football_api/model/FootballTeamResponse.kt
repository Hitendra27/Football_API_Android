package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class FootballTeamResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: Boolean
)
package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("note")
    val note: Note,
    @SerializedName("stats")
    val stats: List<Stat>,
    @SerializedName("team")
    val team: FootballTeam
)
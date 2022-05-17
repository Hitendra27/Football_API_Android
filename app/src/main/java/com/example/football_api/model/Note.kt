package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("color")
    val color: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rank")
    val rank: Int
)
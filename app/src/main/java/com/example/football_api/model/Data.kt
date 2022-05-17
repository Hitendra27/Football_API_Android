package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("season")
    val season: Int,
    @SerializedName("seasonDisplay")
    val seasonDisplay: String,
    @SerializedName("standings")
    val standings: MutableList<Standing>
)
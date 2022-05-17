package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class FootballTeam(
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("location")
    val location: String,
    @SerializedName("logos")
    val logos: List<Logo>,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortDisplayName")
    val shortDisplayName: String,
    @SerializedName("uid")
    val uid: String
)
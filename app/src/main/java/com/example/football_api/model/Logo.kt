package com.example.football_api.model


import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("alt")
    val alt: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("href")
    val href: String,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("rel")
    val rel: List<String>,
    @SerializedName("width")
    val width: Int
)
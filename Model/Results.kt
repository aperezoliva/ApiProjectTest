package com.example.apiproject.Model

import com.google.gson.annotations.SerializedName

data class Results (
    @SerializedName("name")
    val gameName: String,
    @SerializedName("original_release_date")
    val gameReleaseDate: String,
    @SerializedName("deck")
    val gameDescription: String
)

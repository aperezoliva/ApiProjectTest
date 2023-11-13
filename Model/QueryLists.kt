package com.example.apiproject.Model

import com.google.gson.annotations.SerializedName

data class QueryLists(
    @SerializedName("results")
    val queryResults: String
)

package com.example.apiproject

import retrofit2.Call
import retrofit2.http.GET

interface SteamApi {
    @GET("todos")
    fun getData(): Call<List<ProfileModel>>
}
package com.example.apiproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SteamApi {
    @GET("api/game/{game_id}/?api_key=aca91106803f2d208306ac80f79813389eb6e56c&format=json&field_list=genres,name")

    fun getData(@Path("game_id") id: String): Call<ProfileModel>
}
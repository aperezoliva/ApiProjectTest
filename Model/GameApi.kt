package com.example.apiproject.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://square.github.io/retrofit/ documentation helped on working with
// retrofit, it's wonderful how far actually reading gets you
interface GameApi {
    @GET("api/games/?api_key=aca91106803f2d208306ac80f79813389eb6e56c")

    fun getData(@Query("format") format: String, @Query("filter") name: String): Call<ProfileModel>
}
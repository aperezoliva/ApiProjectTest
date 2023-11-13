package com.example.apiproject.Controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.apiproject.Model.GameApi
import com.example.apiproject.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserControls()
{
        fun sendRequest(
            ctx: Context,
            gameName: MutableState<TextFieldValue>,
            responseReturn: MutableList<MutableList<String>>
        ) {
            val BASE_URL = "https://www.giantbomb.com"

            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(GameApi::class.java)


            val retrofitData = retrofitBuilder.getData("JSON", "name:" + gameName.value.text)

            retrofitData.enqueue(object : Callback<ProfileModel?> {
                override fun onResponse(
                    call: Call<ProfileModel?>,
                    response: Response<ProfileModel?>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            ctx,
                            "Successfully retrieved data from Giant Bomb",
                            Toast.LENGTH_SHORT
                        ).show()
                        val responseBody = response.body()
                        responseReturn.clear()
                        responseBody?.results?.forEach { it ->
                            val tempList =
                                mutableListOf(it.gameName, it.gameDescription, it.gameReleaseDate)
                            responseReturn.add(tempList)
                        }

                        Log.d(
                            "Main",
                            "success! " + gameName.value.text + "Response: " + responseReturn.toString()
                        )

                    }
                }

                override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                    Log.d("Main", t.message.toString())
                }
            })



    }
}
package com.example.apiproject.Controller

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.TextFieldValue
import com.example.apiproject.Model.GameApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiModel() {
    var responseReturnValue: MutableList<MutableList<String>?> = mutableStateListOf()
    fun sendRequest(
        ctx: Context,
        gameName: MutableState<TextFieldValue>,

        ) {
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        val BASE_URL = "https://www.giantbomb.com"

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GameApi::class.java)


        val retrofitData = retrofitBuilder.getData("JSON", "name:" + gameName.value.text)
        try {
            val responseBody = retrofitData.execute()?.body()
            Toast.makeText(
                ctx,
                "Successfully retrieved data from Giant Bomb",
                Toast.LENGTH_SHORT
            ).show()

            responseReturnValue.clear()
            responseBody?.results?.forEach { it ->
                val tempList =
                    mutableListOf(it.gameName, it.gameDescription, it.gameReleaseDate)
                responseReturnValue.add(tempList)
            }

            Log.d(
                "Main",
                "success! " + gameName.value.text + "Response: " + responseReturnValue.toString()
            )
        } catch (e: NoSuchFieldException) {
            Log.d("Catch", "Error on line 17 of ApiModel")
        }



//            retrofitData.execute(object : Callback<ProfileModel?> {
//                override fun onResponse(
//                    call: Call<ProfileModel?>,
//                    response: Response<ProfileModel?>
//                ) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(
//                            ctx,
//                            "Successfully retrieved data from Giant Bomb",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        val responseBody = response.body()
//                        responseReturnValue.clear()
//                        responseBody?.results?.forEach { it ->
//                            val tempList =
//                                mutableListOf(it.gameName, it.gameDescription, it.gameReleaseDate)
//                            responseReturnValue.add(tempList)
//                        }
//
//                        Log.d(
//                            "Main",
//                            "success! " + gameName.value.text + "Response: " + responseReturnValue.toString()
//                        )
//
//                    }
//                }
//
//                override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
//                    Log.d("Main", t.message.toString())
//                }
//            })

    }
}
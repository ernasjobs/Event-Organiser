package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.api.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }

}
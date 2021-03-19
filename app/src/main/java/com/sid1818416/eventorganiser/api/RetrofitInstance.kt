package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.api.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * A HTTP Client to connect and interact with the server
 */
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
    val postsApi: PostsApi by lazy {
        retrofit.create(PostsApi::class.java)
    }
    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}
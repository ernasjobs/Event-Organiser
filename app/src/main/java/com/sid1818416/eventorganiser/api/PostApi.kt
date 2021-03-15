package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("posts/2")
    suspend fun getPost(): Response<Post>
}
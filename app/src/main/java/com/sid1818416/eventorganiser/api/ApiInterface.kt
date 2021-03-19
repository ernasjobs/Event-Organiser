package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.Post
import com.sid1818416.eventorganiser.database.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * List of functions for retrofit to generate
 */
interface PostApi {
    @GET("posts/3")
    suspend fun getPost(): Response<Post>
}
interface PostsApi {
    @GET("posts")
    //val posts : Call<List<Post?>?>?
    suspend fun getPosts(): Response<List<Post>>
}

interface UserApi {
    @GET("users/2")
    suspend fun getUser(): Response<User>
}
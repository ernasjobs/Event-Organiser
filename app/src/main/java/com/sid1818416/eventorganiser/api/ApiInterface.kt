package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.database.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * List of functions for retrofit to generate
 */
interface PostApi {
    @GET("posts/3")
    suspend fun getPost(): Response<Post>
    @GET("posts/{postNumber}")
    suspend fun getPost2(
            @Path("postNumber") number: Int
    ): Response<Post>
}
interface PostsApi {
    @GET("GetEvents/In Progress")
    //val posts : Call<List<Post?>?>?
   suspend fun getPosts(): Response<List<Post>>
}

interface UserApi {
    @GET("users/2")
    suspend fun getUser(): Response<User>
}
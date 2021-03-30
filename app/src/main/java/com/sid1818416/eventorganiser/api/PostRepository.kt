package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.models.Post
import retrofit2.Response

class PostRepository {
    suspend fun getPost(): Response<Post> {
      return  RetrofitInstance.api.getPost()
    }
    suspend fun getPosts(): Response<List<Post>> {
        return  RetrofitInstance.postsApi.getPosts()
    }
    suspend fun  getPost2(number: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }
}
package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.Post
import com.sid1818416.eventorganiser.database.User
import retrofit2.Response

class PostRepository {
    suspend fun getPost(): Response<Post> {
      return  RetrofitInstance.api.getPost()
    }
    suspend fun getPosts(): Response<List<Post>> {
        return  RetrofitInstance.postsApi.getPosts()
    }
}
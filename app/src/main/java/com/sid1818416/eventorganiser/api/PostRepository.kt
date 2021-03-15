package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.Post
import retrofit2.Response

class PostRepository {
    suspend fun getPost(): Response<Post>{
      return  RetrofitInstance.api.getPost()
    }
}
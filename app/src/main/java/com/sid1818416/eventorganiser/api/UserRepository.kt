package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.models.User
import retrofit2.Response

class UserRepository {
    suspend fun getUser(): Response<User>{
      return  RetrofitInstance.userApi.getUser()
    }

}
package com.sid1818416.eventorganiser.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid1818416.eventorganiser.api.UserRepository
import com.sid1818416.eventorganiser.database.User
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel (private val repository: UserRepository): ViewModel() {
    val myResponse: MutableLiveData<Response<User>> = MutableLiveData()
    fun getUser(){

        viewModelScope.launch {
            val response = repository.getUser()
          myResponse.value = response
        }
    }
}
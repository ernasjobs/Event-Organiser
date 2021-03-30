package com.sid1818416.eventorganiser.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.database.models.Post
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val repository: PostRepository): ViewModel() {
   val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(){

        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}
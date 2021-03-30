package com.sid1818416.eventorganiser.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.database.models.Post
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val repository: PostRepository): ViewModel() {
    init {
        Log.i("MYTAG","inside_users_Lisrt_init")
    }
   val myResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    fun getPosts(){

        viewModelScope.launch {
            val response = repository.getPosts()
            myResponse.value = response
        }
    }
}
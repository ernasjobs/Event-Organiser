package com.sid1818416.eventorganiser.posts

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.database.RegisterDatabase
import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.database.models.ToDoData
import com.sid1818416.eventorganiser.database.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val repository: PostRepository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    init {
        Log.i("MYTAG","Inside Posts View Model")
        viewModelScope.launch {
            val response = repository.getPosts()
            myResponse.value = response
        }
    }
//    fun getPosts(){
//
//        viewModelScope.launch {
//            val response = repository.getPosts()
//            myResponse.value = response
//        }
//    }
}



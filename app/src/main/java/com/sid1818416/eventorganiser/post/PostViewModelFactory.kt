package com.sid1818416.eventorganiser.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.api.PostRepository

class PostViewModelFactory(
    private val repository: PostRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}
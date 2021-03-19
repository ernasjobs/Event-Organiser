package com.sid1818416.eventorganiser.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.api.PostRepository

class PostsViewModelFactory(
    private val repository: PostRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repository) as T
    }
}
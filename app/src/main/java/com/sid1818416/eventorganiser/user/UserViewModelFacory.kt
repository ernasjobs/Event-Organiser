package com.sid1818416.eventorganiser.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.api.UserRepository
import com.sid1818416.eventorganiser.post.PostViewModel

class UserViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}
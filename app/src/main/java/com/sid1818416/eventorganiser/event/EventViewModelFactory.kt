package com.sid1818416.eventorganiser.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.api.EventRepository

class EventViewModelFactory(
    private val repository: EventRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventViewModel(repository) as T
    }
}
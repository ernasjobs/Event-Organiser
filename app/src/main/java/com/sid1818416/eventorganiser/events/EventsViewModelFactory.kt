package com.sid1818416.eventorganiser.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.api.EventRepository

class EventsViewModelFactory(
    private val repository: EventRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventsViewModel(repository) as T
    }
}
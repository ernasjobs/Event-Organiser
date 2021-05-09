package com.sid1818416.eventorganiser.events

import androidx.lifecycle.*
import com.sid1818416.eventorganiser.api.EventRepository
import com.sid1818416.eventorganiser.database.models.Event
import kotlinx.coroutines.launch
import retrofit2.Response

class EventsViewModel(private val repository: EventRepository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<Event>>> = MutableLiveData()
    init {
        viewModelScope.launch {
            val response = repository.getEvents()
            myResponse.value = response
        }
    }
}



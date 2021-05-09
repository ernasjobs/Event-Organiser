package com.sid1818416.eventorganiser.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sid1818416.eventorganiser.api.EventRepository
import com.sid1818416.eventorganiser.database.models.Event
import kotlinx.coroutines.launch
import retrofit2.Response

class EventViewModel(private val repository: EventRepository): ViewModel() {
   val myResponse: MutableLiveData<Response<Event>> = MutableLiveData()

    fun getPost(){

        viewModelScope.launch {
            val response = repository.getEvent()
            myResponse.value = response
        }
    }
}
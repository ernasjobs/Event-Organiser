package com.sid1818416.eventorganiser.api

import com.sid1818416.eventorganiser.database.models.Event
import retrofit2.Response

class EventRepository {
    suspend fun getEvent(): Response<Event> {
      return  RetrofitInstance.api.getEvent()
    }
    suspend fun getEvents(): Response<List<Event>> {
        return  RetrofitInstance.EVENTS_API.getEvents()
    }
    suspend fun  getPost2(number: Int): Response<Event>{
        return RetrofitInstance.api.getPost2(number)
    }
}
package com.sid1818416.eventorganiser.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class Event (
        val id: Int,
        val eventName: String,
        val eventDescription: String,
        val eventStartDateTime: String,
        val eventEndDateTime: String,
        val eventAddress1: String,
        val eventAddress2: String,
        val eventPostcode : String,
        val eventCategory: String,
        val eventStatus: String,
        val avatar : String

        ): Parcelable

data class EventResponse (
        val pages: Int,
        val data: List<Event>
)
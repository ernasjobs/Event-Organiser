package com.sid1818416.eventorganiser.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
        ): Parcelable

data class PostsResponse (
        val pages: Int,
        val data: List<Post>
)
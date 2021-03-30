package com.sid1818416.eventorganiser.database.models

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
        )

data class PostsResponse (
        val pages: Int,
        val data: List<Post>
)
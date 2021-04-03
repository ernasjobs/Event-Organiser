package com.sid1818416.eventorganiser.posts

import androidx.recyclerview.widget.DiffUtil
import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.database.models.ToDoData

class PostDiffUtil (
    private val oldList: List<Post>,
    private val newList: List<Post>
): DiffUtil.Callback()
{
    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].id == newList[newItemPosition].id
               && return oldList[oldItemPosition].eventDescription == newList[newItemPosition].eventDescription
               && return oldList[oldItemPosition].eventStatus == newList[newItemPosition].eventStatus
               && return oldList[oldItemPosition].eventPostcode == newList[newItemPosition].eventPostcode

    }

}

package com.sid1818416.eventorganiser.todofragments.list

import androidx.recyclerview.widget.DiffUtil
import com.sid1818416.eventorganiser.database.models.ToDoData

class ToDoDiffUtil (
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
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
               && return oldList[oldItemPosition].title == newList[newItemPosition].title
               && return oldList[oldItemPosition].description == newList[newItemPosition].description
               && return oldList[oldItemPosition].priority == newList[newItemPosition].priority

    }

}

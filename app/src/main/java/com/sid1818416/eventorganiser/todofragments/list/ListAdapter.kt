package com.sid1818416.eventorganiser.todofragments.list

import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Priority
import com.sid1818416.eventorganiser.database.models.ToDoData
import com.sid1818416.eventorganiser.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var dataList = emptyList<ToDoData>()

    class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil = ToDoDiffUtil (dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff((toDoDiffUtil))
        this.dataList = toDoData
        // notifies recycler view that the data has changed
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}
package com.sid1818416.eventorganiser.events

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid1818416.eventorganiser.database.models.Event
import com.sid1818416.eventorganiser.databinding.EventItemBinding

class EventAdapter : RecyclerView.Adapter<EventAdapter.MyPostViewHolder>(){
    var dataList = emptyList<Event>()

    class MyPostViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(event: Event){
            binding.event = event
            Log.i("MYTAG", "Binding Post data")
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyPostViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventItemBinding.inflate(layoutInflater, parent, false)
                return MyPostViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        return MyPostViewHolder.from(parent)
    }
    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        holder.bind(dataList[position])


    }
    fun setData(event: List<Event>){
       // val postDiffUtil = PostDiffUtil (dataList, post)
        //val postDiffResult = DiffUtil.calculateDiff((postDiffUtil))
        this.dataList = event
        notifyDataSetChanged();
        Log.i("MYTAG", "Inside Datalist")
        //Log.i("MYTAG", dataList.toString())
        // notifies recycler view that the data has changed
        //postDiffResult.dispatchUpdatesTo(this)
    }

}




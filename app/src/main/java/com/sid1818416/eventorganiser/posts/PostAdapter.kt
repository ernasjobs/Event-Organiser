package com.sid1818416.eventorganiser.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyPostViewHolder>(){
    var dataList = emptyList<Post>()

    class MyPostViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.post = post
            Log.i("MYTAG", "Binding Post data")
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyPostViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostItemBinding.inflate(layoutInflater, parent, false)
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
    fun setData(post: List<Post>){
       // val postDiffUtil = PostDiffUtil (dataList, post)
        //val postDiffResult = DiffUtil.calculateDiff((postDiffUtil))
        this.dataList = post
        notifyDataSetChanged();
        Log.i("MYTAG", "Inside Datalist")
        //Log.i("MYTAG", dataList.toString())
        // notifies recycler view that the data has changed
        //postDiffResult.dispatchUpdatesTo(this)
    }

}




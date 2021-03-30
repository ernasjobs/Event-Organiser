package com.sid1818416.eventorganiser.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.databinding.PostItemBinding

class PostViewAdapter(private val postsList :List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: PostItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.post_item,parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postsList[position])

    }


}

class PostViewHolder(private val binding : PostItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(post: Post){
        binding.userIdText.text = post.id.toString()
        binding.titleText.text = post.title
        binding.bodyText.text = post.body
    }

}




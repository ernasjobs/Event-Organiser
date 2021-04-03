package com.sid1818416.eventorganiser.posts

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Post
import com.bumptech.glide.Glide

class PostsBindingAdapters {
    companion object {
        @BindingAdapter("android:sendDataToPostFragment")
        @JvmStatic
        fun navigateToPostFragment (view: CardView, currenPost: Post){
            view.setOnClickListener{
                val action = PostsFragmentDirections.actionPostsFragmentToPostFragment(currenPost)
                view.findNavController().navigate(action)
            }
        }
        @BindingAdapter("android:imageUrl")
        @JvmStatic
        fun bindImage(imageView: ImageView, url: String?) {
            Glide.with(imageView)
                    .load(url)
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .error(R.drawable.ic_baseline_account_circle_24)
                    .into(imageView)
        }
    }
}
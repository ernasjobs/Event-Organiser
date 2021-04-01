package com.sid1818416.eventorganiser.posts

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.sid1818416.eventorganiser.database.models.Post

class PostsBindingAdapters {
    companion object {
        @BindingAdapter("android:sendDataToPostFragment")
        @JvmStatic
        fun navigateToPostFragment (view: ConstraintLayout, currenPost: Post){
            view.setOnClickListener{
                val action = PostsFragmentDirections.actionPostsFragmentToPostFragment(currenPost)
                view.findNavController().navigate(action)
            }
        }
    }
}
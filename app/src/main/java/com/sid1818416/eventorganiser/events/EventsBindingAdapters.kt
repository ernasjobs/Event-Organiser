package com.sid1818416.eventorganiser.events

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Event
import com.bumptech.glide.Glide

class EventsBindingAdapters {
    companion object {
        @BindingAdapter("android:sendDataToPostFragment")
        @JvmStatic
        fun navigateToPostFragment (view: CardView, currenEvent: Event){
            view.setOnClickListener{
                val action = EventsFragmentDirections.actionPostsFragmentToPostFragment(currenEvent)
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
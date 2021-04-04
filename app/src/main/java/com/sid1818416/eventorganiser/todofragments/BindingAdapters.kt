package com.sid1818416.eventorganiser.todofragments

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Priority
import com.sid1818416.eventorganiser.database.models.ToDoData
import com.sid1818416.eventorganiser.todofragments.list.ListFragmentDirections
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapters {

    companion object {
    @BindingAdapter("android:navigateToAddFragment")
    @JvmStatic
        fun  navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                if (navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }
        @BindingAdapter("android:navigateToListFragment")
        @JvmStatic
        fun  navigateToListFragment(view: Button, navigate: Boolean){
            view.setOnClickListener{
                if (navigate){
                    view.findNavController().navigate(R.id.action_postFragment_to_listFragment)
                }
            }
        }
        @BindingAdapter("android:emptyTable")
        @JvmStatic
        fun emptyTable(view: View, emptyTable: MutableLiveData<Boolean>){
            when(emptyTable.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }

        }
        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
            when(priority){
                Priority.HIGH -> {
                    view.setSelection(0)
                }
                Priority.MEDIUM -> {
                    view.setSelection(1)
                }
                Priority.LOW -> {
                    view.setSelection(2)
                }
            }
        }
        @RequiresApi(Build.VERSION_CODES.M)
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority){
            when(priority){
                Priority.HIGH -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
                }
                Priority.MEDIUM -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
                }
                Priority.LOW -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
                }
            }
        }
        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData){
            view.setOnClickListener{
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
        @SuppressLint("SimpleDateFormat")
        @BindingAdapter("android:convertDateToString")
        @JvmStatic
        fun convertDateToString(view: TextView, dateString: String){
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd-MMM-yyyy  HH:mm")
            try {
                val formattedDate = formatter.format(parser.parse(dateString))
                System.out.println(formattedDate)
                view.setText("Event Date: "+ formattedDate)
            } catch (e: ParseException) {
                e.printStackTrace()
        }
        }

    }

}
package com.sid1818416.eventorganiser.todofragments

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.Event
import com.sid1818416.eventorganiser.database.models.Priority
import com.sid1818416.eventorganiser.database.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val emptyTable : MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfToDoDataTableEmpty(toDoData: List<ToDoData>){
        emptyTable.value = toDoData.isEmpty()
    }
    fun checkIfPostTableEmpty(post: List<Event>){
        emptyTable.value = post.isEmpty()
        Log.i("MYTAG", "Check If Posts api disconnected")
    }
    val listener : AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position){
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

     fun verifyDataFromUser(title: String, description: String): Boolean {
         return !(title.isEmpty() || description.isEmpty())
    }
     fun  parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

}
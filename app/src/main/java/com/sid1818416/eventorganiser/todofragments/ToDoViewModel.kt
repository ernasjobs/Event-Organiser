package com.sid1818416.eventorganiser.todofragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sid1818416.eventorganiser.database.RegisterDatabase
import com.sid1818416.eventorganiser.database.models.RegisterEntity
import com.sid1818416.eventorganiser.database.models.ToDoData
import com.sid1818416.eventorganiser.database.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel (application) {
    private val toDoDao = RegisterDatabase.getInstance(application).toDoDao()
    private val repository: ToDoRepository
     val getAllData: LiveData<List<ToDoData>>
     val sortByHighPriority: LiveData<List<ToDoData>>
     val sortByLowPriority: LiveData<List<ToDoData>>
    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowhPriority
    }
    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }
    fun updateData(toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateData(toDoData)
        }
    }
    fun deleteItem (toDoData: ToDoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }
    fun deleteAll (){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
    fun searchDatabase (searchQuery: String): LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery)
    }



}
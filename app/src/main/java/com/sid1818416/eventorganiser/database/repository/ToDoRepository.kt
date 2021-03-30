package com.sid1818416.eventorganiser.database.repository

import androidx.lifecycle.LiveData
import com.sid1818416.eventorganiser.database.ToDoDao
import com.sid1818416.eventorganiser.database.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()
    val sortByHighPriority : LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowhPriority : LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()
    suspend fun insertData(toDoData: ToDoData)
    {
        toDoDao.insertData(toDoData)
    }
    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }
    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }
    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
        return toDoDao.searchDatabase((searchQuery))
    }

}
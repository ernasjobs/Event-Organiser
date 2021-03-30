package com.sid1818416.eventorganiser.database.repository

import android.util.Log
import com.sid1818416.eventorganiser.database.RegisterDatabaseDao
import com.sid1818416.eventorganiser.database.models.RegisterEntity

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return dao.getUsername(userName)
    }
    //suspend fun deleteAll(): Int {
    //    return dao.deleteAll()
    //}

}
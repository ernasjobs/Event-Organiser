package com.sid1818416.eventorganiser.userDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sid1818416.eventorganiser.database.repository.RegisterRepository

class UserDetailsViewModel (private val repository: RegisterRepository, application: Application):AndroidViewModel(application){

    val users = repository.users
    init {
        Log.i("MYTAG","inside_users_Lisrt_init")
    }


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto
        get() = _navigateto

    fun doneNavigating(){
        _navigateto.value=false
    }

    fun backButtonclicked(){
        _navigateto.value = true
    }

}
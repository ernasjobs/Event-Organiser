package com.sid1818416.eventorganiser.login
import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import kotlinx.coroutines.*
import com.sid1818416.eventorganiser.database.RegisterRepository

class LoginViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {
    init {
        Log.i("MYTAG", "init")
    }

    val users = repository.users

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails

    private val _navigatetoUserInfo = MutableLiveData<Boolean>()

    val navigatetoUserInfo: LiveData<Boolean>
        get() = _navigatetoUserInfo

    private val _navigatetoPostDetails = MutableLiveData<Boolean>()

    val navigatetoPostDetails: LiveData<Boolean>
        get() = _navigatetoPostDetails



    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun signUP() {
        _navigatetoRegister.value = true
    }

    fun loginButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    if(usersNames.passwrd == inputPassword.value){
                        inputUsername.value = null
                        inputPassword.value = null
                     //   _navigatetoUserDetails.value = true
                     _navigatetoPostDetails.value = true
                       // _navigatetoUserInfo.value = true
                    }else{
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUsername.value = true
                }
            }
        }
    }




    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }
    fun doneNavigatingUserInfo() {
        _navigatetoUserInfo.value = false
    }

    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }
    fun doneNavigatingPostDetails() {
        _navigatetoPostDetails.value = false
    }


    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastErrorUsername() {
        _errorToastUsername .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}
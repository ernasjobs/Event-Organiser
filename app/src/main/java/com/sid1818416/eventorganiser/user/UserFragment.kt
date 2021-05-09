package com.sid1818416.eventorganiser.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.api.UserRepository
import com.sid1818416.eventorganiser.databinding.FragmentEventBinding
import com.sid1818416.eventorganiser.databinding.FragmentUserBinding


class UserFragment() : Fragment() {
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_user, container, false
        )
        Log.d("Test", "Test ok")
        val repository = UserRepository()
        val userModelFactory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, userModelFactory).get(UserViewModel::class.java)

        binding.myUserViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getUser()
        Log.d("Test", "Test ok1")
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful)
            {
                  Log.d("Test", "Test ok")
//                Log.d("Response", response.body()?.id.toString())
//                Log.d("Response", response.body()?.email!!)
//                Log.d("Response", response.body()?.first_name!!)
//                Log.d("Response", response.body()?.last_name!!)
//                // binding.avatar. = response.body()?.avatar!!
//                 binding.firstName.text = response.body()?.first_name!!
//                binding.email.text = response.body()?.email!!
            }
            else
            {
                Log.d("Response", response.errorBody().toString())
                binding.firstName.text = response.code().toString()
            }


        })
        return binding.root
    }

}
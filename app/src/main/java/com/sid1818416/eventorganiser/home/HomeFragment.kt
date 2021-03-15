package com.sid1818416.eventorganiser.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.sid1818416.eventorganiser.AppNavigator
import com.sid1818416.eventorganiser.R

class HomeFragment : Fragment() {
    private lateinit var appNavigator: AppNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val submitButton: Button = view.findViewById(R.id.button)
        submitButton.setOnClickListener { appNavigator.navigateToRegister() }
        val loginLink: TextView = view.findViewById(R.id.loginLink)
        loginLink.setOnClickListener{appNavigator.navigateToLogin()}
        // Inflate the layout for this fragment
        return view
    }


}
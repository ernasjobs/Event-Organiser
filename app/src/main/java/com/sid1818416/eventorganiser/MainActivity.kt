package com.sid1818416.eventorganiser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sid1818416.eventorganiser.home.HomeFragmentDirections
import com.sid1818416.eventorganiser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))

    }

    override fun navigateToLogin() {
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController(R.id.myNavHostFragment).navigate(action)

    }
    override fun navigateToRegister() {
        val action = HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
        findNavController(R.id.myNavHostFragment).navigate(action)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController((R.id.myNavHostFragment))
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
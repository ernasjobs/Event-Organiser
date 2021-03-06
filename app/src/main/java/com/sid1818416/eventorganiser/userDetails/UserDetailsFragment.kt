package com.sid1818416.eventorganiser.userDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.repository.RegisterRepository
import com.sid1818416.eventorganiser.databinding.UserDetailsFragmentBinding
import com.sid1818416.eventorganiser.database.RegisterDatabase


class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: UserDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_details_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = UserDetalisFactory(repository, application)

        userDetailsViewModel =
            ViewModelProvider(this, factory).get(UserDetailsViewModel::class.java)

        binding.userDetailsLayout = userDetailsViewModel

        binding.lifecycleOwner = this

//        userDetailsViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
//            if (hasFinished == true) {
//                val action = UserDetailsFragmentDirections.actionUserDetailsFragmentToLoginFragment()
//                NavHostFragment.findNavController(this).navigate(action)
//                userDetailsViewModel.doneNavigating()
//            }
//        })

        initRecyclerView()

        return binding.root

    }


    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        userDetailsViewModel.users.observe(viewLifecycleOwner, Observer {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }

}
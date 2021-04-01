package com.sid1818416.eventorganiser.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private val args by navArgs<PostFragmentArgs>()
    private var _binding : FragmentPostBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = this

        return binding.root
    }

}
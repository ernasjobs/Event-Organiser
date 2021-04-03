package com.sid1818416.eventorganiser.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sid1818416.eventorganiser.databinding.FragmentPostBinding
import java.text.DateFormat
import java.util.*

class PostFragment : Fragment() {

    private val args by navArgs<PostFragmentArgs>()
    private var _binding : FragmentPostBinding? = null
   // var currentDateTimeString: String = DateFormat.getDateInstance().format(Date())
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = this
      //  binding.dateTimeTxtView.setText(currentDateTimeString)
        return binding.root
    }



}
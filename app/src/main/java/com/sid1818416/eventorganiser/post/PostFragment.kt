package com.sid1818416.eventorganiser.post

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.navArgs
import com.sid1818416.eventorganiser.MapFragment
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.databinding.FragmentPostBinding
import java.text.DateFormat
import java.util.*

class PostFragment : Fragment() {

    lateinit var context: AppCompatActivity
    private val args by navArgs<PostFragmentArgs>()
    private var _binding : FragmentPostBinding? = null
   // var currentDateTimeString: String = DateFormat.getDateInstance().format(Date())
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as AppCompatActivity
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        binding.args = args
        binding.lifecycleOwner = this
      //  binding.dateTimeTxtView.setText(currentDateTimeString)
        val fm = context.supportFragmentManager
        val fragmentTransaction: FragmentTransaction
        val fragment = MapFragment()
        fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.mapFragment, fragment)
            .addToBackStack(null)
        fragmentTransaction.commit()
        return binding.root
    }



}
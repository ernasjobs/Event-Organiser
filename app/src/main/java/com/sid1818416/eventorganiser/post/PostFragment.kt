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
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.databinding.FragmentPostBinding

class PostFragment : Fragment() {
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPostBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post, container, false
        )
        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        binding.myPostViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getPost()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful)
            {
                Log.d("Response", response.body()?.userid.toString())
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)
                binding.textView.text = response.body()?.title!!
            }
            else
            {
                Log.d("Response", response.errorBody().toString())
                binding.textView.text = response.code().toString()
            }


        })
        return binding.root
    }

}
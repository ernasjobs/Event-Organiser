package com.sid1818416.eventorganiser.posts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818416.eventorganiser.AppNavigator
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.database.models.Post
import com.sid1818416.eventorganiser.databinding.FragmentPostsBinding
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class PostsFragment : Fragment() {

    private var _binding:  FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PostsViewModel
    private val adapter: PostAdapter by lazy { PostAdapter() }

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data binding
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val repository = PostRepository()
        val viewModelFactory = PostsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)

        // setup RecyclerView
        initRecyclerView()
       // viewModel.getPosts()
        // Observe LiveData
        Log.i("MYTAG", "Passed View Model GetPost")
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
           // Log.i("MYTAG", response.body().toString())
            adapter.setData(response.body()  as List<Post>)
        })
        return binding.root
    }
    private fun initRecyclerView() {
        val recyclerView = binding.postsRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }
       // binding.postsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        Log.i("MYTAG", "Inside Posts Details Fragment")
       // displayPostsList()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


//    private fun displayPostsList() {
//        Log.i("MYTAG", "Inside1 ...UserDetails..Fragment")
//        viewModel.getPosts()
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
//            if(response.isSuccessful)
//            {
//                Log.i("MYTAG", "Inside12 ...UserDetails..Fragment")
//                binding.postsRecyclerView.adapter = PostViewAdapter(response.body()  as List<Post>)
//            }
//            else
//            {
//                Log.d("Response", response.errorBody().toString())
//                //binding.textView.text = response.code().toString()
//            }
//        })
//    }
}



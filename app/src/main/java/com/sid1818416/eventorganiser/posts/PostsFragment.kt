package com.sid1818416.eventorganiser.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.api.PostRepository
import com.sid1818416.eventorganiser.database.Post
import com.sid1818416.eventorganiser.databinding.FragmentPostsBinding
import com.sid1818416.eventorganiser.databinding.UserDetailsFragmentBinding
import com.sid1818416.eventorganiser.userDetails.MyRecycleViewAdapter

class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel
   // private var mAdapter: PostAdapter?= null;
    private var mPosts: MutableList<Post> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_posts, container, false
        )
        val repository = PostRepository()
        val viewModelFactory = PostsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)

        binding.postsViewModel = viewModel
        binding.lifecycleOwner = this


//        viewModel.getPosts()
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
//            if(response.isSuccessful)
//            {
//                var postsList : List <Post> ? = response.body() as List<Post>
//                var post = arrayOfNulls<String>(postsList!!.size)
//                var postBody = arrayOfNulls<String>(postsList!!.size)
//                 for (i in postsList.indices)
//                     post[i] = postsList[i].title
//
//
//              var adapter = ArrayAdapter<String>(binding.root.context,android.R.layout.simple_dropdown_item_1line, post)
//               // binding.listView.setAdapter(adapter)
//               // binding.listView[] = adapter
////                Log.d("Response", response.body()?.userId.toString())
////                Log.d("Response", response.body()?.id.toString())
////                Log.d("Response", response.body()?.title!!)
////                Log.d("Response", response.body()?.body!!)
////                binding.textView.text = response.body()?.title!!
//            }
//            else
//            {
////                Log.d("Response", response.errorBody().toString())
////                binding.textView.text = response.code().toString()
//            }
//
//
//        })
        initRecyclerView()
        return binding.root
    }
    private fun initRecyclerView() {
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        displayPostsList()
    }
    private fun displayPostsList() {
        Log.i("MYTAG", "Inside1 ...UserDetails..Fragment")
        viewModel.getPosts()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful)
            {
                Log.i("MYTAG", "Inside12 ...UserDetails..Fragment")
                binding.postsRecyclerView.adapter = PostViewAdapter(response.body()  as List<Post>)
            }
            else
            {
                Log.d("Response", response.errorBody().toString())
                //binding.textView.text = response.code().toString()
            }
        })
    }
}



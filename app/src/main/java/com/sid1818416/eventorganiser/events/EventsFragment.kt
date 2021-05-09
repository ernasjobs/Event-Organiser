package com.sid1818416.eventorganiser.events

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sid1818416.eventorganiser.AppNavigator
import com.sid1818416.eventorganiser.api.EventRepository
import com.sid1818416.eventorganiser.database.models.Event
import com.sid1818416.eventorganiser.databinding.FragmentEventsBinding
import com.sid1818416.eventorganiser.todofragments.SharedViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class EventsFragment : Fragment() {

    private var _binding:  FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EventsViewModel
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val adapter: EventAdapter by lazy { EventAdapter() }

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
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel
        val repository = EventRepository()
        val viewModelFactory = EventsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventsViewModel::class.java)

        // setup RecyclerView
        initRecyclerView()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            mSharedViewModel.checkIfPostTableEmpty(response.body()  as List<Event>)
            adapter.setData(response.body()  as List<Event>)
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
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



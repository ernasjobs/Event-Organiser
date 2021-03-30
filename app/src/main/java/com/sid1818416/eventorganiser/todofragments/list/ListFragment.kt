package com.sid1818416.eventorganiser.todofragments.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sid1818416.eventorganiser.AppNavigator
import com.sid1818416.eventorganiser.R
import com.sid1818416.eventorganiser.database.models.ToDoData
import com.sid1818416.eventorganiser.databinding.FragmentListBinding
import com.sid1818416.eventorganiser.todofragments.SharedViewModel
import com.sid1818416.eventorganiser.todofragments.ToDoViewModel
import com.sid1818416.eventorganiser.utils.hideKeyboard
import com.sid1818416.eventorganiser.utils.observeOnce
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class ListFragment : Fragment(), SearchView.OnQueryTextListener {
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private var _binding:  FragmentListBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup Recycler View
        setupRecyclerView()
        // observe live data
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfToDoDataTableEmpty(data)
            adapter.setData(data)
        })
        // Set Menu
        setHasOptionsMenu(true)
        // Hide Soft Keyboard
        hideKeyboard(requireActivity())

        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        // put recyclerview items in a grid
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
       // recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }
        // Swipe to Delete
        swipeToDelete(recyclerView)
    }
    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.absoluteAdapterPosition]
                // Delete Item
                mToDoViewModel.deleteItem(deletedItem)
                adapter.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
              //  Toast.makeText(requireContext(), "Sucessfully removed: '${deletedItem.title}'", Toast.LENGTH_SHORT).show()
                // Restore Deleted Item
                 restoreDeletedData(viewHolder.itemView, deletedItem, viewHolder.absoluteAdapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun restoreDeletedData(view: View, deletedItem: ToDoData, position: Int ){
    val snacBar = Snackbar.make(
            view, "Deleted '${deletedItem.title}'",
            Snackbar.LENGTH_LONG
    )
        snacBar.setAction("Undo"){
            mToDoViewModel.insertData(deletedItem)
          //  adapter.notifyItemChanged(position)
        }
        snacBar.show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.todo_list_fragment_menu,menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ){
            R.id.menu_delete_all ->  confirmRemoval()
            R.id.menu_priority_high -> mToDoViewModel.sortByHighPriority.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
            R.id.menu_priority_low -> mToDoViewModel.sortByLowPriority.observe(viewLifecycleOwner, Observer { adapter.setData(it) })
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query !=null){
            searchThroughDatabse(query)
        }
        return true
    }

    private fun searchThroughDatabse(query: String) {
        val searchQuery = "%$query%"
        mToDoViewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner, Observer {list ->
            list?.let {
                Log.d("ListFragment", "search through database")
                adapter.setData(it)  }

        })
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query !=null){
            searchThroughDatabse(query)
        }
        return true
    }
    // Show AlertDialog to confirm removal of all items from database
    private fun confirmRemoval()
    {
        var builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
            _, _ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(),
                    "Everything removed succesfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ ->}
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to remove everything?")
        builder.create().show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
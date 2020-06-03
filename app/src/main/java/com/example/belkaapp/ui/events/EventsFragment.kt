package com.example.belkaapp.ui.events

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.belkaapp.R
import com.example.belkaapp.databinding.FragmentEventsBinding
import com.example.belkaapp.databinding.GridViewItemBinding


class EventsFragment : Fragment() {

    private val viewModel: EventsViewModel by lazy {
        ViewModelProviders.of(this).get(EventsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("EventsFragment", "EventsFragment created!")
        val binding = FragmentEventsBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()
        setHasOptionsMenu(true)
        return binding.root    
    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(EventsViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}
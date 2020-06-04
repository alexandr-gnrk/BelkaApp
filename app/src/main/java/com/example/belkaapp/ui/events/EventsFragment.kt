package com.example.belkaapp.ui.events

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

//import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.belkaapp.R
import com.example.belkaapp.databinding.FragmentEventsBinding
import com.example.belkaapp.databinding.GridViewItemBinding
import com.example.belkaapp.ui.detail.DetailViewModel
//import com.example.belkaapp.ui.events.EventsFragmentDirections


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
        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
//                action_eventsFragment_to_detailFragment
                this.findNavController().navigate(
                        EventsFragmentDirections
                                .actionEventsFragmentToDetailFragment(it))
                // Must find the NavController from the Fragment                     EventsFragmentDirections
//                this.findNavController().navigate(EventsFragmentDirections.action_showDetail(it))
//                 Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })
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
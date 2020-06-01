package com.example.belkaapp.ui.suggest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.belkaapp.R

class SuggestFragment : Fragment() {

    companion object {
        fun newInstance() = SuggestFragment()
    }

    private lateinit var viewModel: SuggestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_suggest, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SuggestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
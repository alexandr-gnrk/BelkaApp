package com.example.belkaapp.ui.suggest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.belkaapp.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.example.belkaapp.databinding.FragmentSuggestBinding
import com.example.belkaapp.ui.inform.InformViewModel

class SuggestFragment : Fragment() {

    companion object {
        fun newInstance() = SuggestFragment()
    }
    private lateinit var binding: FragmentSuggestBinding
    private lateinit var viewModel: SuggestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SuggestFragment", "SuggestFragment created!")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_suggest, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SuggestViewModel::class.java)
        viewModel.context.value = context
        viewModel.eventName.observe(viewLifecycleOwner, Observer { new_name ->
            binding.eventName.setText(new_name)
        })
        viewModel.eventDate.observe(viewLifecycleOwner, Observer { new_date ->
            binding.eventDate.setText(new_date)
        })
        viewModel.eventDescription.observe(viewLifecycleOwner, Observer { new_description ->
            binding.eventDesciption.setText(new_description)
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer { new_msg ->
            Toast.makeText(activity, new_msg, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.suggestButton.setOnClickListener {
            viewModel.makeSuggestion(
                binding.eventName.text.toString(),
                binding.eventDate.text.toString(),
                binding.eventDesciption.text.toString())
        }
    }

}
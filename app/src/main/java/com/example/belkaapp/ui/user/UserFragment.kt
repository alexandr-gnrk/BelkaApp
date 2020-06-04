package com.example.belkaapp.ui.user

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.belkaapp.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.example.belkaapp.databinding.UserFragmentBinding

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: UserFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("UserFragment", "UserFragment created!")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.user_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        if (context == null) {
            Log.i("UserFragment", "Context is null")
        }
        else {
            Log.i("UserFragment", "Context is not null")
        }
        viewModel.context.value = context
        viewModel.loadUserFromStorage()
        viewModel.user.observe(viewLifecycleOwner, Observer { new_user ->
            binding.editTextTextPersonName.setText(new_user.name)
            binding.editTextTextPersonName2.setText(new_user.surname)
            binding.editTextPhone.setText(new_user.phone_number)
            binding.editTextTextEmailAddress.setText(new_user.email)
        })
        viewModel.msg.observe(viewLifecycleOwner, Observer { new_msg ->
            Toast.makeText(activity, new_msg, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            viewModel.updateUser(
                User(
                    binding.editTextTextPersonName.text.toString(),
                    binding.editTextTextPersonName2.text.toString(),
                    binding.editTextPhone.text.toString(),
                    binding.editTextTextEmailAddress.text.toString())

            )
        }
    }

}
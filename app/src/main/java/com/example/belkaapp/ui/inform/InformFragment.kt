package com.example.belkaapp.ui.inform

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.belkaapp.R
import com.google.android.material.textfield.TextInputEditText
import com.example.belkaapp.databinding.FragmentInformBinding

class InformFragment : Fragment() {
    companion object {
        fun newInstance() = InformFragment()
    }

    private lateinit var viewModel: InformViewModel
    private lateinit var binding: FragmentInformBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("InformFragment", "Inform fragment created!")

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_inform, container, false
        )
//        return inflater.inflate(R.layout.fragment_inform, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InformViewModel::class.java)
        viewModel.context.value = context
        viewModel.reason.observe(viewLifecycleOwner, Observer { new_id ->
            binding.reasonRadioGroup.clearCheck()
        })
        viewModel.description.observe(viewLifecycleOwner, Observer { new_reason ->
            binding.descriptionText.setText(new_reason)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button : Button = binding.reportButton

        button.setOnClickListener { inform(view) }
    }

    private fun inform(view: View) {
        val radio_group : RadioGroup = binding.reasonRadioGroup
        val text_input: TextInputEditText = binding.descriptionText
        var id: Int = radio_group.checkedRadioButtonId
        if (id != -1) {
            val radio:RadioButton =  view.findViewById(id)
            val msg: String = viewModel.informAbout(radio.text.toString(), text_input.text.toString())
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity,"Спочатку виберіть причину", Toast.LENGTH_SHORT).show()
        }

    }
}
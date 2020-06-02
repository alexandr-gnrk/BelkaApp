package com.example.belkaapp.ui.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.belkaapp.R
import com.example.belkaapp.ui.report.ReportViewModel

//class ReportFragment : Fragment() {
//    private lateinit var reportViewModel: ReportViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        reportViewModel =
////            ViewModelProvider(this).get(ReportViewModel::class.java)
////        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
//
//        val root = inflater.inflate(R.layout.fragment_report, container, false)
//        return root
//    }
//
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        Log.i("SuggestFragment", "SuggestFragment created!")
////
////        return inflater.inflate(R.layout.user_fragment, container, false)
////    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//}

class ReportFragment : Fragment() {

    companion object {
        fun newInstance() = ReportFragment()
    }

    private lateinit var viewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ReportFragment", "ReportFragment created!")

        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

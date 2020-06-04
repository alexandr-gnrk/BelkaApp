package com.example.belkaapp.ui.inform

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.belkaapp.R
import com.google.android.material.textfield.TextInputEditText
import com.example.belkaapp.databinding.FragmentInformBinding
import com.google.android.gms.location.*

class InformFragment : Fragment() {
    companion object {
        fun newInstance() = InformFragment()
    }

    private lateinit var viewModel: InformViewModel
    private lateinit var binding: FragmentInformBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("InformFragment", "Inform fragment created!")

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_inform, container, false
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

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
        viewModel.msg.observe(viewLifecycleOwner, Observer { new_msg ->
            Toast.makeText(activity, new_msg, Toast.LENGTH_SHORT).show()
        })
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button : Button = binding.reportButton
        currView = view
        button.setOnClickListener {
            getLastLocation()
        }
    }

    private fun inform(view: View) {
        val radio_group : RadioGroup = binding.reasonRadioGroup
        val text_input: TextInputEditText = binding.descriptionText
        var id: Int = radio_group.checkedRadioButtonId
        if (id != -1) {
            val radio:RadioButton =  view.findViewById(id)
            viewModel.informAbout(radio.text.toString(), text_input.text.toString())
//            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity,"Спочатку виберіть причину", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                42
        )
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            fusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                var location: Location? = task.result
                requestNewLocationData()
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.requestLocationUpdates(
                mLocationRequest, locationCallback,
                Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            informIfNearToBelkaSpace(lastLocation)
        }
    }

    private fun informIfNearToBelkaSpace(location: Location) {
        val maxDistance = 100
        val belkaSpaceLocation = Location("")
        belkaSpaceLocation.latitude = 50.4497
        belkaSpaceLocation.longitude = 30.4558
        if (location.distanceTo(belkaSpaceLocation) <= maxDistance) {
            inform(currView)
        } else {
            Toast.makeText(context, "Ви знаходитесь занадто далеко від Белки", Toast.LENGTH_LONG).show()
        }
    }
}
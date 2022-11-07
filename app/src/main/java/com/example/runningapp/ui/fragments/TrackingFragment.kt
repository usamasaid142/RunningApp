package com.example.runningapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.runningapp.R
import com.example.runningapp.databinding.FragmentStatisticBinding
import com.example.runningapp.databinding.FragmentTrackingBinding
import com.example.runningapp.service.TrackingService
import com.example.runningapp.ui.viewmodels.MainViewmodel
import com.example.runningapp.utils.Constants
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment() {

    private val viewmodel: MainViewmodel by viewModels()
    private lateinit var binding: FragmentTrackingBinding
    private var map:GoogleMap?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTrackingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapview.onCreate(savedInstanceState)

        binding.mapview.getMapAsync {
            map=it
        }
        initButton()
    }

    private fun initButton(){
        binding.start.setOnClickListener {
            sendServiceCommand(Constants.Action_Start_Or_Resume_Services)
        }
    }
    private fun sendServiceCommand(action:String)=
        Intent(requireContext(),TrackingService::class.java).also {
            it.action=action
            requireContext().startService(it)
        }

    override fun onResume() {
        super.onResume()
        binding.mapview.onResume()
    }

    override fun onStop() {
        super.onStop()
    binding.mapview.onStop()
    }

    override fun onStart() {
        super.onStart()
        binding.mapview.onStart()

    }

    override fun onPause() {
        super.onPause()
        binding.mapview.onPause()

    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapview.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapview.onSaveInstanceState(outState)
    }

}
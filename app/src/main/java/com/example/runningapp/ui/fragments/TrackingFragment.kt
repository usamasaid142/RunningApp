package com.example.runningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.runningapp.R
import com.example.runningapp.databinding.FragmentStatisticBinding
import com.example.runningapp.databinding.FragmentTrackingBinding
import com.example.runningapp.ui.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment() {

    private val viewmodel: MainViewmodel by viewModels()
    private lateinit var binding: FragmentTrackingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentTrackingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}
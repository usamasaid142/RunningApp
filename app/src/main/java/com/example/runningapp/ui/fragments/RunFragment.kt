package com.example.runningapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runningapp.R
import com.example.runningapp.TrackingUtilities
import com.example.runningapp.alertdialog.Alert
import com.example.runningapp.databinding.FragmentRunBinding
import com.example.runningapp.ui.viewmodels.MainViewmodel
import com.example.runningapp.utils.Constants.request_code_location_permission
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


@AndroidEntryPoint
class RunFragment : Fragment(),EasyPermissions.PermissionCallbacks {

    private val viewmodel:MainViewmodel by viewModels()
    private lateinit var binding:FragmentRunBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRunBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inintbutton()
       // requestPermission()
        if(isLocationPermissionAllowed()){

        }else{
            requestLocationPermission()
        }

    }

    fun inintbutton(){
     binding.btnFab.setOnClickListener {
         findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
     }
    }

    private fun requestPermission() {
        if (TrackingUtilities.hasLocationPermission(requireContext())) {
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.hasPermissions(
                requireContext(),
                "You need to accept location for using app",
                request_code_location_permission.toString(),
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
        } else {

            EasyPermissions.hasPermissions(
                requireContext(),
                "You need to accept location for using app",
                request_code_location_permission.toString(),
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION

            )

        }

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }



    fun isLocationPermissionAllowed(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
        return false
    }

    private fun requestLocationPermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION))
        {

            // فى حالة رفض اول مره يعمل برمشن
            // explain why you need the permission
            // عمل بوب لتوضيح سبب البرمش
            //  ثم طلب البرمشن

            Alert.showMessage(
                requireContext(), R.string.weneedpermissiontoaccesslocation, R.string.worning, true, R.string.ok,
            ) { p0, _ ->
                p0?.dismiss()
                requestPermissionLauncher.launch( arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,))
 //               requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }


        }else
        {

            // request permission

            requestPermissionLauncher.launch( arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                ))
            //requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
    }

        val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            val granted = permissions.entries.all {
                it.value == true
            }
            permissions.entries.forEach {
                if (it.value) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Toast.makeText(requireContext(),"good",Toast.LENGTH_LONG).show()

                }else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(requireContext(),"you can not use this app",Toast.LENGTH_LONG).show()
                }
            }

                }






}
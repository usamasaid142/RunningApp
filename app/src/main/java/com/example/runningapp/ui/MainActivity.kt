package com.example.runningapp.ui

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.runningapp.R
import com.example.runningapp.databinding.ActivityMainBinding
import com.example.runningapp.utils.Constants.Action_Show_TrackingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        navigateToTrakingFragment(intent)
        binding.bottomViewNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_,destination,_ ->

            when(destination.id){
                R.id.settingsFragment,R.id.runFragment,R.id.statisticFragment->{
                    binding.bottomViewNav.visibility=View.VISIBLE
                }else ->{
                binding.bottomViewNav.visibility=View.GONE

            }
            }
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrakingFragment(intent)
    }

    private fun navigateToTrakingFragment(intent: Intent?){
        if (intent?.action==Action_Show_TrackingFragment){
           navController.navigate(R.id.action_tracking)
        }
    }
}
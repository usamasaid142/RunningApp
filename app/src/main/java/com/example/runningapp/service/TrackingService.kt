package com.example.runningapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.runningapp.R
import com.example.runningapp.ui.MainActivity
import com.example.runningapp.utils.Constants.Action_Pause_Services
import com.example.runningapp.utils.Constants.Action_Show_TrackingFragment
import com.example.runningapp.utils.Constants.Action_Start_Or_Resume_Services
import com.example.runningapp.utils.Constants.Action_Stop_Services
import com.example.runningapp.utils.Constants.Notification_Id
import com.example.runningapp.utils.Constants.Notification_channel_Id
import com.example.runningapp.utils.Constants.Notification_channel_Name
import com.google.android.gms.maps.model.LatLng
import timber.log.Timber

typealias polyline=MutableList<LatLng>
typealias polylines=MutableList<polyline>
class TrackingService : LifecycleService() {

    var isfirstrun=true

    companion object{
        val istracking=MutableLiveData<Boolean>()
        val pathPoints=MutableLiveData<polylines>()
    }

    private fun postIntialValue(){
        istracking.postValue(false)
        pathPoints.postValue(mutableListOf())
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when (it.action) {
                Action_Start_Or_Resume_Services -> {
                    if (isfirstrun){
                        startForegroundService()
                        isfirstrun=false
                    }else{
                        Log.e("tag", "resume Running...")
                    }

                }
                Action_Pause_Services -> {
                    Timber.d("Pause_services")
                }
                Action_Stop_Services -> {
                    Timber.d("Stop_services")
                }
                else -> {}
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun addPathPoint(location:Location?){
        location?.let {
            val pos=LatLng(location.latitude,location.longitude)

            pathPoints.value?.apply {
                last().add(pos)
                pathPoints.postValue(this)
            }
        }
    }
    private fun addEmptypolyline()= pathPoints.value?.apply {
        add(mutableListOf())
        pathPoints.postValue(this)
    }?: pathPoints.postValue(mutableListOf(mutableListOf()))
    private fun startForegroundService() {
        addEmptypolyline()
        val notifictionamnger =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notifictionamnger)
        }
        val notificationBuilder = NotificationCompat.Builder(this, Notification_channel_Id)
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setSmallIcon(R.drawable.ic_run)
            .setContentIntent(getMainActivityPendingIntent())
        startForeground(Notification_Id,notificationBuilder.build())
    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this, 0,
        Intent(this, MainActivity::class.java).also {
            it.action = Action_Show_TrackingFragment
        }, FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationmanger: NotificationManager) {
        val channel = NotificationChannel(
            Notification_channel_Id,
            Notification_channel_Name, IMPORTANCE_LOW
        )

        notificationmanger.createNotificationChannel(channel)
    }
}
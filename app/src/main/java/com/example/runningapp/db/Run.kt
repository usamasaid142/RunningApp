package com.example.runningapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Running_table")
data class Run (

    var img:Bitmap?=null,
    var timestamp:Long=0L,
    var avgSpeedInKHM:Float=0f,
    var distanceInMeters:Int=0,
    var timeInMilles:Long=0,
    var coloriesburrend:Int=0
        ){
@PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
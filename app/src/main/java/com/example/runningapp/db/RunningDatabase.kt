package com.example.runningapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Run::class], version = 1)
@TypeConverters(Convertrs::class)
abstract class RunningDatabase :RoomDatabase(){

    abstract fun runDao():RunDao
}
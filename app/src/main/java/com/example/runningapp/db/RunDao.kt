package com.example.runningapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(run: Run)
    @Delete
    suspend fun delete(run: Run)

    @Query("Select * from running_table ORDER by timestamp DESC")
    fun getAllSortedRunsByDate():LiveData<List<Run>>

    @Query("Select * from running_table ORDER by timeInMilles DESC")
    fun getAllSortedRunsByTimeinmillisecond():LiveData<List<Run>>

    @Query("Select * from running_table ORDER by coloriesburrend DESC")
    fun getAllSortedRunsByColoriesburned():LiveData<List<Run>>

    @Query("Select * from running_table ORDER by distanceInMeters DESC")
    fun getAllSortedRunsByDistanceinmeters():LiveData<List<Run>>

    @Query("Select * from running_table ORDER by avgSpeedInKHM DESC")
    fun getAllSortedRunsByAvgSpee():LiveData<List<Run>>

    @Query("Select Sum(timeInMilles) From running_table")
    fun getAllTotalTimeimilliscecond():LiveData<Long>

    @Query("Select Sum(coloriesburrend) From running_table")
    fun getAllTotalColoriesburened():LiveData<Int>

    @Query("Select Sum(distanceInMeters) From running_table")
    fun getAllTotalDistanceInMeters():LiveData<Int>

    @Query("Select AVG(avgSpeedInKHM) From running_table")
    fun getAllTotalAvgSpeed():LiveData<Float>
}
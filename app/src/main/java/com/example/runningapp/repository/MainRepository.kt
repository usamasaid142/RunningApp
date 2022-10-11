package com.example.runningapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.runningapp.db.Run
import com.example.runningapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(val runDao: RunDao) {


   suspend fun insertRunning(run:Run)=runDao.insert(run)
   suspend fun deleteRunning(run:Run)=runDao.delete(run)

   fun getAllSortedRunsByDate()=runDao.getAllSortedRunsByDate()

   fun getAllSortedRunsByTimeinmillisecond()=runDao.getAllSortedRunsByTimeinmillisecond()

   fun getAllSortedRunsByColoriesburned()=runDao.getAllSortedRunsByColoriesburned()

   fun getAllSortedRunsByDistanceinmeters()=runDao.getAllSortedRunsByDistanceinmeters()

   fun getAllSortedRunsByAvgSpee()=runDao.getAllSortedRunsByAvgSpee()

   fun getAllTotalTimeimilliscecond()=runDao.getAllTotalTimeimilliscecond()

   fun getAllTotalColoriesburened()=runDao.getAllTotalColoriesburened()
   fun getAllTotalDistanceInMeters()=runDao.getAllTotalDistanceInMeters()

   fun getAllTotalAvgSpeed()=runDao.getAllTotalAvgSpeed()

}
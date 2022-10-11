package com.example.runningapp.di

import android.content.Context
import androidx.room.Room
import com.example.runningapp.db.RunningDatabase
import com.example.runningapp.utils.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Appmodule {

    @Singleton
    @Provides
    fun provideRunningdatabase(
      @ApplicationContext  app:Context

    )=Room.databaseBuilder(app,RunningDatabase::class.java,Constans.Running_databaseNam).build()

    @Singleton
    @Provides
    fun provideRunningDao(db:RunningDatabase)=db.runDao()
}
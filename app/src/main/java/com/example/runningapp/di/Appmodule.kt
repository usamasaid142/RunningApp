package com.example.runningapp.di

import android.content.Context
import androidx.room.Room
import com.example.runningapp.db.RunningDatabase
import com.example.runningapp.utils.Constants.Running_database
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
    fun provideRunDatabse(
       @ApplicationContext app:Context
    )= Room.databaseBuilder(app,RunningDatabase::class.java,Running_database).build()

    @Singleton
    @Provides
    fun provideRunDao(db:RunningDatabase)= db.runDao()

}
package com.example.runningapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.runningapp.repository.MainRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class MainViewmodel @ViewModelScoped constructor(val mainRepository: MainRepository):ViewModel() {


}
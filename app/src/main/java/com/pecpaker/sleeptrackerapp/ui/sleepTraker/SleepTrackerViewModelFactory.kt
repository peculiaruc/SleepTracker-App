package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNightDao
import java.lang.IllegalArgumentException

class SleepTrackerViewModelFactory(
    private val dataSource: SleepNightDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }


}
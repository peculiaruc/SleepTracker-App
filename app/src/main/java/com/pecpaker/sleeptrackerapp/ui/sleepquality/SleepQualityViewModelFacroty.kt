package com.pecpaker.sleeptrackerapp.ui.sleepquality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepDatabase
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNightDao
import java.lang.IllegalArgumentException

class SleepQualityViewModelFacroty(
    private val sleepNight: Long,
    private val dataSource: SleepNightDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(sleepNight, dataSource) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }

}
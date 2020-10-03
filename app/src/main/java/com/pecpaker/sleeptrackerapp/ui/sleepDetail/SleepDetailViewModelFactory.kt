package com.pecpaker.sleeptrackerapp.ui.sleepDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNightDao
import java.lang.IllegalArgumentException

class SleepDetailViewModelFactory(
    private val sleepNightKey: Long,
    private val dataSource: SleepNightDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)) {
            return SleepDetailViewModel(sleepNightKey, dataSource) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }


}
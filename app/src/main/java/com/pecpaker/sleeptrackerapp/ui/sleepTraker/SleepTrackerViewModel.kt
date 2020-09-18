package com.pecpaker.sleeptrackerapp.ui.sleepTraker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pecpaker.sleeptrackerapp.dataSource.local.SleepNightDao

class SleepTrackerViewModel(
    val database: SleepNightDao,
    application: Application
) : AndroidViewModel(application) {

}
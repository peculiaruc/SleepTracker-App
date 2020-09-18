package com.pecpaker.sleeptrackerapp.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepNightDao {

    @Insert
    fun insert(night: SleepNight)

    @Update
    fun updata(night: SleepNight)

    @Query("SELECT * from daily_sleep_quality_night WHERE nightId = :key")
    fun get(key: Long): SleepNight

    @Query("DELETE FROM daily_sleep_quality_night")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quality_night ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

    @Query("SELECT * FROM daily_sleep_quality_night ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?
}
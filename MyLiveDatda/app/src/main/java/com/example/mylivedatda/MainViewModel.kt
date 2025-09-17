package com.example.mylivedatda

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MainViewModel : ViewModel() {
    private val mInitialTime = SystemClock.elapsedRealtime()
    private val mElapsedTime = MutableLiveData<Long?>()// muttable bisa kita ubah valuenya

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)// merubah value
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    // fungsi buat nanti di observer
    fun getElapsedTime(): LiveData<Long?> {// live data hanya read only
        return mElapsedTime
    }

    companion object{
        private const val ONE_SECOND = 1000
    }
}
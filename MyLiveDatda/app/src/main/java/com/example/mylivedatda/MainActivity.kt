package com.example.mylivedatda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mylivedatda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var liveDataTimerViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        subcribe()
    }

    private fun subcribe(){

        // ui yang bakal di updat
        // nilai yang akan di update oleh observe
        val elapsedTimeObserver = Observer<Long?> { valueLong -> // akan selalu di perbarui secara realtime sesuai dengan perubahan yand ada di view model
            val newTeks = resources.getString(R.string.seconds, valueLong)// string format
            binding.tvTimer.text = newTeks
        }

        // proses mengobserve live data
        // yang mengobserve/update realtime
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}
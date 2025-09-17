package com.example.myflexiblefragment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
        Log.i("frag", "fragmaent : ${HomeFragment::class.java.simpleName}")
        Log.i("frag", "fragmaent : $fragment")
        if (fragment !is HomeFragment){
            Log.d("MFF", "fragment name : ${HomeFragment::class.java.simpleName}")

            // tanpa ktx
//            supportFragmentManager.beginTransaction()
//                .add(R.id.frame_container, HomeFragment(), HomeFragment::class.java.simpleName)
//                .commit()

            // with ktx
            supportFragmentManager.commit {
                add(R.id.frame_container, HomeFragment(), HomeFragment::class.java.simpleName)
            }
        }

    }
}
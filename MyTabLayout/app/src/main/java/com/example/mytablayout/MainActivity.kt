package com.example.mytablayout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SectionPagerAdapter(this) // this = letak view pager
        val viewPager: ViewPager2 = findViewById(R.id.view_pager_countainer)
        viewPager.adapter = adapter
        val tavLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tavLayout, viewPager) { tab, position ->
            tab.text = TAB_TITLE[position] // inisialize nama nama tabnya

        }.attach()

        supportActionBar?.elevation = 0f

    }

    companion object{
        private val TAB_TITLE = arrayOf(
            "Home fragment", "Profile fragment", "Tambahan fragment"
        )
    }
}
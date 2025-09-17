package com.example.mytablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = HomeFragment()
        fragment.arguments = Bundle().apply {
            putInt(HomeFragment.ARG_SECTION, position+1)
        }
        return fragment
    }
}
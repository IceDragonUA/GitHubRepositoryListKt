package com.evaluation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evaluation.R
import com.evaluation.favorites.fragment.FavoriteFragment
import com.evaluation.search.fragment.MainFragment
import com.evaluation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        pager.adapter = ScreenSlidePagerAdapter(this)
    }

    override fun onBackPressed() {
        if (pager.currentItem == DEFAULT_POSITION) {
            super.onBackPressed()
        } else {
            pager.currentItem = pager.currentItem - NEXT_POSITION
        }
    }

    private inner class ScreenSlidePagerAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {

        override fun createFragment(position: Int): Fragment =
            when (position) {
                MAIN -> MainFragment()
                FAVORITE -> FavoriteFragment()
                else -> Fragment()
            }

        override fun getItemCount(): Int = FRAGMENTS
    }

}
package com.example.pupil


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pupil.fragments.HomeFragment
import com.example.pupil.fragments.MapFragment
import com.example.pupil.fragments.ReportFragment
import kotlinx.android.synthetic.main.activity_menu.*


class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val homeFragment = HomeFragment()
        val reportFragment = ReportFragment()
        val mapFragment = MapFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_report -> makeCurrentFragment(reportFragment)
                R.id.navigation_map -> makeCurrentFragment(mapFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}

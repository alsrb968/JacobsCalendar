package com.jacobs.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jacobs.calendar.databinding.ActivityMainBinding
import com.jacobs.calendar.view.CalendarFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                dayNames = CalendarController.getShortDayNames()
            }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CalendarFragment.newInstance())
            .commit()
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun refreshCurrDate(calendar: Calendar) {
        mBinding.calendar = calendar
    }
}
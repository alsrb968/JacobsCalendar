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
    private val mCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                calendar = this@MainActivity.mCalendar
                dayNames = this@MainActivity.getShortDayNames()
            }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CalendarFragment.newInstance())
            .commit()

        getDatas()


    }

    private fun getShortDayNames(): ArrayList<String> {
        val ret = ArrayList<String>()
        val calendar = Calendar.getInstance();
        for (day in Calendar.SUNDAY..Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, day)
            ret.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())!!)
        }
        return ret
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getDatas() {
        val calendar = Calendar.getInstance()
        Log.e(calendar.get(Calendar.DAY_OF_WEEK).toString())
        Log.e(calendar.getActualMaximum(Calendar.DAY_OF_MONTH).toString())

    }
}
package com.jacobs.calendar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jacobs.calendar.databinding.ActivityMainBinding
import com.jacobs.calendar.model.CalendarViewModel
import com.jacobs.calendar.view.CalendarFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: CalendarViewModel by viewModels()
    private lateinit var mCalendarController: CalendarController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                dayNames = CalendarController.getShortDayNames()
                listener = Listener()
            }

        mCalendarController = CalendarController()
        mCalendarController.initBaseCalendar {
            refreshCurrDate(it)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CalendarFragment.newInstance())
            .commit()
    }

    inner class Listener {
        fun onPrevMonth() {
            mCalendarController.changeToPrevMonth {
                refreshCurrDate(it)
            }
        }

        fun onNextMonth() {
            mCalendarController.changeToNextMonth {
                refreshCurrDate(it)
            }
        }
    }

    private fun refreshCurrDate(calendar: Calendar) {
        mBinding.calendar = calendar
        mViewModel.replace(mCalendarController.data)
//        for (i in mViewModel.model.value!!) {
//            Log.i("day: ${i.calendar.get(Calendar.DAY_OF_MONTH)}")
//        }
    }

    fun getFocusedCalendar(): Calendar {
        return mBinding.calendar!!.clone() as Calendar
    }
}
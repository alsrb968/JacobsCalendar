package com.jacobs.calendar

import androidx.lifecycle.ViewModel
import com.jacobs.calendar.model.CalendarModel
import com.jacobs.calendar.model.ListLiveData
import java.util.*

class CalendarViewModel : ViewModel() {
    val model: ListLiveData<CalendarModel> = ListLiveData()

//    fun loadModel() {
//
//        val calendar = Calendar.getInstance()
//        val currMonth = calendar.get(Calendar.MONTH) + 1
//Calendar.JANUARY
//        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
//        Log.i("$dayOfWeek")
//        val lastMonthTotalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//        for (i in (lastMonthTotalDay - dayOfWeek) until lastMonthTotalDay) {
//            ret.add(CalendarModel(currMonth - 1, i))
//        }
//
//        val currMonthTotalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
//        for (i in 1..currMonthTotalDay) {
//            ret.add(CalendarModel(currMonth, i));
//        }
//
//        val remain = 42 - ret.size
//        for (i in 1..remain) {
//            ret.add(CalendarModel(currMonth + 1, i));
//        }
//
//        Log.i("${ret.size}")
//
//    }
}
package com.jacobs.calendar

import com.jacobs.calendar.model.CalendarModel
import java.util.*

class CalendarController {

    companion object {
        const val DAYS_OF_WEEK = 7
        const val LOW_OF_CALENDAR = 6

        fun getShortDayNames(): ArrayList<String> {
            val ret = arrayListOf<String>()
            val calendar = Calendar.getInstance()
            for (day in Calendar.SUNDAY..Calendar.SATURDAY) {
                calendar.set(Calendar.DAY_OF_WEEK, day)
                ret.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())!!)
            }
            return ret
        }

        @JvmStatic
        fun getSimpleDate(calendar: Calendar): String {
            return String.format("%s, %d %s",
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()))
        }
    }

    val calendar: Calendar = Calendar.getInstance()

    private var prevMonthTailOffset = 0
    private var nextMonthHeadOffset = 0
    private var currMonthMaxDate = 0

    var data = arrayListOf<CalendarModel>()

    init {
        calendar.time = Date()
    }

    fun initBaseCalendar(refreshCallback: (Calendar) -> Unit) {
        makeMonthDate(refreshCallback)
    }

    fun changeToPrevMonth(refreshCallback: (Calendar) -> Unit) {
        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
            calendar.set(Calendar.MONTH, Calendar.DECEMBER)
        } else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        }
        makeMonthDate(refreshCallback)
    }

    fun changeToNextMonth(refreshCallback: (Calendar) -> Unit) {
        if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1)
            calendar.set(Calendar.MONTH, Calendar.JANUARY)
        } else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
        }
        makeMonthDate(refreshCallback)
    }


    private fun makeMonthDate(refreshCallback: (Calendar) -> Unit) {
        data.clear()

        calendar.set(Calendar.DATE, 1)

        currMonthMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        prevMonthTailOffset = calendar.get(Calendar.DAY_OF_WEEK) - 1

        makePrevMonthTail(calendar.clone() as Calendar)
        makeCurrMonth(calendar.clone() as Calendar)

        nextMonthHeadOffset = LOW_OF_CALENDAR * DAYS_OF_WEEK - (prevMonthTailOffset + currMonthMaxDate)
        makeNextMonthHead(calendar.clone() as Calendar)

        refreshCallback(calendar)
    }

    private fun makePrevMonthTail(calendar: Calendar) {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)

        val maxDate = calendar.getActualMaximum(Calendar.DATE)
        val maxOffsetDate = maxDate - prevMonthTailOffset

        calendar.set(Calendar.DAY_OF_MONTH, maxOffsetDate)

        for (i in 1..prevMonthTailOffset) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
            data.add(CalendarModel(calendar.clone() as Calendar))
        }
    }

    private fun makeCurrMonth(calendar: Calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        for (i in 1..calendar.getActualMaximum(Calendar.DATE)) {
            data.add(CalendarModel(calendar.clone() as Calendar))
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }
    }

    private fun makeNextMonthHead(calendar: Calendar) {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)

        for (i in 1..nextMonthHeadOffset) {
            data.add(CalendarModel(calendar.clone() as Calendar))
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }
    }


}
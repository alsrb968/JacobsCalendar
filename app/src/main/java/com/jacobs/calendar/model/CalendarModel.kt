package com.jacobs.calendar.model

import java.util.*
import kotlin.collections.ArrayList

data class CalendarModel(
    var calendar: Calendar) {

    var events = ArrayList<String>()
}
package com.jacobs.calendar.model

import androidx.lifecycle.ViewModel
import com.jacobs.calendar.model.CalendarModel
import com.jacobs.calendar.model.ListLiveData
import java.util.*
import kotlin.collections.ArrayList

class CalendarViewModel : ViewModel() {
    val model: ListLiveData<CalendarModel> = ListLiveData()

    fun replace(modelList: ArrayList<CalendarModel>) {
        model.clear(false)
        model.addAll(modelList)
    }
}
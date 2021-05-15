package com.jacobs.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jacobs.calendar.CalendarController
import com.jacobs.calendar.Log
import com.jacobs.calendar.MainActivity
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.FragmentCalendarBinding
import com.jacobs.calendar.model.CalendarModel
import com.jacobs.calendar.popup.AddEventPopup
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    private lateinit var mBinding: FragmentCalendarBinding
    private lateinit var mCalendarController: CalendarController

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        mCalendarController = CalendarController()
        mCalendarController.initBaseCalendar {
            (activity as MainActivity).refreshCurrDate(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentCalendarBinding>(
            inflater, R.layout.fragment_calendar,
            container, false
        ).also {
            mBinding = it.apply {
                recyclerView.layoutManager = GridLayoutManager(this@CalendarFragment.context, CalendarController.DAYS_OF_WEEK)
                val adapter = CalendarAdapter(this@CalendarFragment.context!!, mCalendarController.data)
                for (i in mCalendarController.data) {
                    Log.i("${i.calendar.get(Calendar.DAY_OF_MONTH)}")
                }
                Log.i("${mCalendarController.data.size}")
                adapter.setOnItemClickListener(object : CalendarAdapter.OnItemClickListener {
                    override fun onClick(view: View, position: Int, model: CalendarModel) {
                        Log.i("position: $position, model: $model")
                        val addEventPopup = AddEventPopup(this@CalendarFragment.context, model)
                        addEventPopup.show()
                    }
                })
                recyclerView.adapter = adapter

            }
        }.root


//    private fun getModelList(): ArrayList<CalendarModel> {
//        val ret = ArrayList<CalendarModel>()
//
//        val calendar = Calendar.getInstance()
//        val currMonth = calendar.get(Calendar.MONTH) + 1
//
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
//        return ret
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
            CalendarFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}
package com.jacobs.calendar.view

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.jacobs.calendar.*
import com.jacobs.calendar.databinding.FragmentCalendarBinding
import com.jacobs.calendar.model.CalendarModel
import com.jacobs.calendar.model.CalendarViewModel
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
    private lateinit var mCalendarAdapter: CalendarAdapter

    private val mViewModel: CalendarViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentCalendarBinding>(
            inflater, R.layout.fragment_calendar,
            container, false
        ).also { it ->
            mBinding = it.apply {
                recyclerView.layoutManager =
                    GridLayoutManager(this@CalendarFragment.context, CalendarController.DAYS_OF_WEEK)
                recyclerView.addItemDecoration(DividerItemDecoration(this@CalendarFragment.context, DividerItemDecoration.VERTICAL))
            }

            mViewModel.model.observe(requireActivity(), Observer { arrayCalendarModel ->
                for (i in arrayCalendarModel!!) {
//                    Log.w(i.events.toString())
                    mCalendarAdapter = CalendarAdapter(requireContext(), mViewModel)
                    mCalendarAdapter.setOnItemClickListener(mOnItemClickListener)
                    mBinding.recyclerView.adapter = mCalendarAdapter
                }
            })
        }.root

    private var mOnItemClickListener = object : CalendarAdapter.OnItemClickListener {
        override fun onClick(view: View, position: Int, model: CalendarModel) {
            AddEventPopup.Builder(this@CalendarFragment.context)
                .model(model)
                .listener(object : AddEventPopup.OnTodoEventListener {
                    override fun onTodoEvent(event: String) {
                        model.events.add(event)
                        Log.d("model.event.size: ${model.events.size}")
                        Log.d(mViewModel.model.value!![position].events.toString())
                        mViewModel.model.notifyChange()
                    }
                })
                .show()
        }
    }

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
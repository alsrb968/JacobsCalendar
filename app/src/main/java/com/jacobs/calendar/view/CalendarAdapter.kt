package com.jacobs.calendar.view

import android.content.Context
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jacobs.calendar.MainActivity
import com.jacobs.calendar.model.CalendarViewModel
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.ItemCalendarBinding
import com.jacobs.calendar.model.CalendarModel
import kotlin.math.acos

class CalendarAdapter(var context: Context, var viewModel: CalendarViewModel) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    private var mInflater: LayoutInflater? = null

    interface OnItemClickListener {
        fun onClick(view: View, position: Int, model: CalendarModel)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this@CalendarAdapter.onItemClickListener = onItemClickListener
    }

    class ViewHolder(var binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<ItemCalendarBinding>(
            mInflater!!, R.layout.item_calendar, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val modelList = viewModel.model.value!!
            model = modelList[position]
            if (modelList[position].calendar.get(Calendar.MONTH) != (context as MainActivity).getFocusedCalendar().get(Calendar.MONTH)) {
                root.isEnabled = false
                root.alpha = .3f
            }
            if (modelList[position].calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dayTextView.setTextColor(context.getColor(R.color.red))
            } else if (modelList[position].calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                dayTextView.setTextColor(context.getColor(R.color.blue))
            }
            root.setOnClickListener {
                onItemClickListener?.onClick(it, position, modelList[position])
            }

            val adapter = TodoAdapter(context, modelList[position].events)
            recyclerView.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return viewModel.model.value!!.size
    }
}
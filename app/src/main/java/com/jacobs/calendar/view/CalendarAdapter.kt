package com.jacobs.calendar.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.ItemCalendarBinding
import com.jacobs.calendar.model.CalendarModel

class CalendarAdapter(var modelList: ArrayList<CalendarModel>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private var mInflater: LayoutInflater? = null

    class ViewHolder(var binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<ItemCalendarBinding>(mInflater!!, R.layout.item_calendar, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            model = modelList[position]
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}
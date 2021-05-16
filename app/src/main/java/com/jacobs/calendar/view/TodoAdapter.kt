package com.jacobs.calendar.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.ItemTodoBinding

class TodoAdapter(var context: Context, var todoList: ArrayList<String>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var mInflater: LayoutInflater? = null;

    class ViewHolder(var binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(context)
        }

        val binding = DataBindingUtil.inflate<ItemTodoBinding>(
            mInflater!!, R.layout.item_todo, null, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            todo = todoList[position]
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}
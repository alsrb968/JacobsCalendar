package com.jacobs.calendar.popup

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.databinding.DataBindingUtil
import com.jacobs.calendar.Log
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.PopupAddEventBinding
import com.jacobs.calendar.model.CalendarModel

class AddEventPopup(context: Context?, builder: Builder) {
    private val dialog: Dialog

    interface OnTodoEventListener {
        fun onTodoEvent(event: String)
    }

    private var onTodoEventListener: OnTodoEventListener? = null

    class Builder(var context: Context?) {
        var calendarModel: CalendarModel? = null;
        var onTodoEventListener: OnTodoEventListener? = null;

        fun listener(listener: OnTodoEventListener): Builder {
            this@Builder.onTodoEventListener = listener
            return this
        }

        fun model(model: CalendarModel): Builder {
            this@Builder.calendarModel = model
            return this
        }

        fun show(): AddEventPopup {
            return AddEventPopup(context, this@Builder)
        }
    }

    init {
        val binding: PopupAddEventBinding =
            DataBindingUtil.inflate<PopupAddEventBinding?>(
                LayoutInflater.from(context), R.layout.popup_add_event, null, false)
                .apply {
                    model = builder.calendarModel
                    todoEvent = ""
                    listener = this@AddEventPopup.Listener()
                    editText.setOnEditorActionListener { v, actionId, event ->
                        Listener().onConfirm(todoEvent.toString())
                        false
                    }
                    this@AddEventPopup.onTodoEventListener = builder.onTodoEventListener
                }

        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)

        val window = dialog.window
        val layoutParam = WindowManager.LayoutParams()
        layoutParam.copyFrom(window!!.attributes)
        layoutParam.dimAmount = .6f
        layoutParam.gravity = Gravity.CENTER
        window.attributes = layoutParam
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        show()
    }

    private fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    private fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    inner class Listener {
        fun onConfirm(todoEvent: String) {
            Log.i("todoEvent: $todoEvent")
            onTodoEventListener?.onTodoEvent(todoEvent)
            dismiss()
        }
    }
}
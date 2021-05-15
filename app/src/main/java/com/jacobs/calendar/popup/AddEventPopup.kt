package com.jacobs.calendar.popup

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.jacobs.calendar.Log
import com.jacobs.calendar.R
import com.jacobs.calendar.databinding.PopupAddEventBinding
import com.jacobs.calendar.model.CalendarModel

class AddEventPopup(context: Context?, calendarModel: CalendarModel) {
    private val dialog: Dialog



    init {
        val binding: PopupAddEventBinding =
            DataBindingUtil.inflate<PopupAddEventBinding?>(LayoutInflater.from(context), R.layout.popup_add_event, null, false)
                .apply {
                    editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//                            Log.i("event $event")
                            dismiss()
                            return false
                        };

                    })
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

        binding.apply {
            model = calendarModel
            popup = this@AddEventPopup
        }
    }

    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun onConfirm(event: String) {
        Log.i("event $event")

        dismiss()
    }
}
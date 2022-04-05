package com.iyanuoluwa.todoapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun formatDate(date : Date) : String {
            val simpleDateFormat = SimpleDateFormat("EEE, MM d")
            return simpleDateFormat.format(date)
        }

        fun hideSoftKeyboard(view : View) {
            val input : InputMethodManager = view.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

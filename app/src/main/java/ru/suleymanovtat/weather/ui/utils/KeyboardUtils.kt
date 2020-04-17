package ru.suleymanovtat.weather.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment?.hideKeyboard() = this?.activity.hideKeyboard()

fun Activity?.hideKeyboard() {
    val view = this?.currentFocus ?: return
    this.hideKeyboard(view)
}

fun Activity?.hideKeyboard(view: View) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

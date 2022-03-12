package com.aldev.moviecataloguemade.common

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
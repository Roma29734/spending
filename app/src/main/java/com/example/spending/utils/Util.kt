package com.example.spending.utils

import android.content.Context

object Util {

    fun Context.dpTopx(dp: Int): Float {
        return dp.toFloat() * this.resources.displayMetrics.density
    }
}
package com.example.demo_scsoft.callback

import android.view.View
import java.text.FieldPosition

interface ItemClick {
    fun setOnItemClick(view : View, position: Int)
}
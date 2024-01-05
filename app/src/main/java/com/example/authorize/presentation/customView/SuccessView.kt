package com.example.authorize.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.authorize.R

class SuccessView(context: Context, attrs: AttributeSet? = null): RelativeLayout(context, attrs) {

    val successMessage: TextView = findViewById(R.id.successMessage)
    val closeButton: Button = findViewById(R.id.closeButton)

    init {
        LayoutInflater.from(context).inflate(R.layout.success_view, this, true)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun setMessage(message: String) {
        successMessage.text = message
    }
}
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

    val successMessage: TextView
    val closeButton: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.success_view, this, true)
        successMessage = findViewById(R.id.successMessage)
        closeButton = findViewById(R.id.closeButton)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun setMessage(message: String) {
        successMessage.text = message
    }
}
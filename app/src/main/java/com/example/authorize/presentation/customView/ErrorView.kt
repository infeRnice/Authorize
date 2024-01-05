package com.example.authorize.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.authorize.R

class ErrorView(context: Context, attrs: AttributeSet? = null): ConstraintLayout(context, attrs) {
    val errorMessage: TextView
    val requestNewCodeButton: Button
    val closeButton: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.error_view, this, true)
        errorMessage = findViewById(R.id.errorMessage)
        requestNewCodeButton = findViewById(R.id.requestNewCodeButton)
        closeButton = findViewById(R.id.closeButton)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun setMessage(message: String) {
        errorMessage.text = message
    }
}
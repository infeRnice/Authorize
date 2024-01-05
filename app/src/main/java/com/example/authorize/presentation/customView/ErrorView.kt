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
    val errorMessage: TextView = findViewById(R.id.errorMessage)
    val requestNewCodeButton: Button = findViewById(R.id.requestNewCodeButton)
    val closeButtonErrorView: Button = findViewById(R.id.closeButtonErrorView)

    init {
        LayoutInflater.from(context).inflate(R.layout.error_view, this, true)
    }

    fun hide() {
        visibility = View.GONE
    }

    fun setMessage(message: String) {
        errorMessage.text = message
    }
}
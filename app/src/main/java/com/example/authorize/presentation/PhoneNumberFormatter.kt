package com.example.authorize.presentation

import android.text.Editable
import android.text.TextWatcher

object PhoneNumberFormatter: TextWatcher {
    private var isManualChange = false
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //
    }

    override fun afterTextChanged(s: Editable?) {
        if (isManualChange) {
            return
        }
        isManualChange = true

        val currentText = s.toString()
        if(currentText.isEmpty() || !currentText.startsWith("+")) {
            s?.clear()
            s?.append("+")
            s?.append(currentText.filter { it.isDigit() })
            /*s?.insert(0, "+")*/
        } else {
            val filteredText = "+" + currentText.drop(1).filter { it.isDigit() }
            s?.replace(0, s.length, filteredText)
        }

        isManualChange = false
    }
}
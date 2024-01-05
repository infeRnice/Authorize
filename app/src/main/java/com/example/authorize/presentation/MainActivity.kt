package com.example.authorize.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.authorize.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, AuthFragment())
                .commit()
        }
    }
}
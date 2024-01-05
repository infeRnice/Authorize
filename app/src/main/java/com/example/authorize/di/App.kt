package com.example.authorize.di

import android.app.Application
import com.example.authorize.data.AuthRepository
import com.example.authorize.network.ApiServiceBuilder

class App: Application() {
    lateinit var repository: AuthRepository

    override fun onCreate() {
        super.onCreate()
        val apiService = ApiServiceBuilder.apiService
        repository = AuthRepository(apiService)
    }
}
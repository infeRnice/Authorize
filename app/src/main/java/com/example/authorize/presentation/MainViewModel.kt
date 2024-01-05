package com.example.authorize.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.authorize.data.AuthRepository
import com.example.authorize.network.ApiErrorResponse
import com.example.authorize.network.ApiResponse

class MainViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getCode(phoneNumber: String, successCallback: (ApiResponse) -> Unit, errorCallback: (ApiErrorResponse) -> Unit) {
        Log.d("ViewModel", "ViewModel: getCode called with phoneNumber: $phoneNumber")
        repository.getCode(phoneNumber, successCallback, errorCallback)
    }

    fun getToken(login: String, pasword: String, successCallback: (String) -> Unit, errorCallback: (ApiErrorResponse) -> Unit) {
        repository.getToken(login, pasword, successCallback, errorCallback)
    }
}
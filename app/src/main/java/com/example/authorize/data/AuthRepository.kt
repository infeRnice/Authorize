package com.example.authorize.data

import android.util.Log
import com.example.authorize.network.ApiErrorResponse
import com.example.authorize.network.ApiResponse
import com.example.authorize.network.ApiService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {

    fun getCode(
        phoneNumber: String,
        successCallback: (ApiResponse) -> Unit,
        errorCallback: (ApiErrorResponse) -> Unit
    ) {
        Log.d("Repo", "Repository: getCode called with phoneNumber: $phoneNumber")

        val mockedResponse = ApiResponse(code = "111111", status = "new")
        val mockedErrorResponse = ApiErrorResponse(error = "Телефон должен совпадать с заданным форматом.")

        Log.d("Repo", "intent got repo")
        successCallback(mockedResponse)
        // errorCallback(mockedErrorResponse)

        //так как сервер не отвечает, то мы используем моковые данные
        /*apiService.getCode(phoneNumber).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                Log.d("Repo", "onResponse called")

                if (response.isSuccessful) {
                    response.body()?.let { successCallback(it) }
                } else if (response.code() == 422) {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.string(),
                        ApiErrorResponse::class.java
                    )
                    errorCallback(errorResponse)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Обработка сбоя в запросе
                Log.d("Repo", "ApiService Failure: ${t.message}")
            }
        })*/
        Log.d("Repo", "After calling ApiService")
    }

    fun getToken(
        login: String,
        pasword: String,
        successCallback: (String) -> Unit,
        errorCallback: (ApiErrorResponse) -> Unit
    ) {

        if (pasword == "111111") {
            val token = "89764654643132131"
            successCallback(token)
        } else {
            val errorResponse = ApiErrorResponse(error = "Неверный код")
            errorCallback(errorResponse)
        }

        /*apiService.getToken(login, pasword).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {



                if (response.isSuccessful) {
                    successCallback(response.body() ?: "")
                } else if (response.code() == 422) {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.string(),
                        ApiErrorResponse::class.java
                    )
                    errorCallback(errorResponse)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //обработка слоя в запросе
            }
        })*/
    }
}
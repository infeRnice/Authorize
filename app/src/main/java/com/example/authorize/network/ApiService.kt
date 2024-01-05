package com.example.authorize.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/getCode")
    fun getCode(@Query("login") login: String): Call<ApiResponse>

    @GET("/getToken")
    fun getToken(@Query("login") login: String, @Query("password") password: String): Call<String>
}

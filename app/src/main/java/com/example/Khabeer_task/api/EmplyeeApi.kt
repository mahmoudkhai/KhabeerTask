package com.example.Khabeer_task.api

import com.example.Khabeer_task.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmplyeeApi {
    @GET("/GetPayroll")
    suspend fun getEmployeeInformation ():Response<ApiResponse?>
}
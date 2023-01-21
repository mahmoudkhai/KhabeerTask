package com.example.Khabeer_task.repository

import com.example.Khabeer_task.model.ApiResponse
import com.example.Khabeer_task.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface EmployeeSalaryRepository {
    fun getEmployeeInformation () : Flow<NetworkResponse<ApiResponse?>>
}
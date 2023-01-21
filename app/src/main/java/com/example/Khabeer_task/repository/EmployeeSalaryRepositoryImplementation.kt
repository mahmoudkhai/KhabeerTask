package com.example.Khabeer_task.repository

import com.example.Khabeer_task.api.EmplyeeApi
import com.example.Khabeer_task.model.ApiResponse
import com.example.Khabeer_task.util.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmployeeSalaryRepositoryImplementation @Inject constructor(private val api: EmplyeeApi) :
    EmployeeSalaryRepository {

    override fun getEmployeeInformation(): Flow<NetworkResponse<ApiResponse?>> {
        return flow {
            emit(NetworkResponse.Loading())
            try {
                val response = api.getEmployeeInformation()

                if (response.isSuccessful) {
                    emit(NetworkResponse.Success(response.body()))
                } else {
                    emit(
                        NetworkResponse.Error(
                            isNetworkError = false,
                            errorMessage = response.message()
                        )
                    )
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(isNetworkError = true, errorMessage = e.message))
            }
        }
    }


}
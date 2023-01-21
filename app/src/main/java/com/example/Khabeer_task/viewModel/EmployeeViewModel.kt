package com.example.Khabeer_task.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.Khabeer_task.model.ApiResponse
import com.example.Khabeer_task.repository.EmployeeSalaryRepository
import com.example.Khabeer_task.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val repo : EmployeeSalaryRepository) : ViewModel(){

    val apiResponse : LiveData<NetworkResponse<ApiResponse?>> = repo.getEmployeeInformation().asLiveData()

}
package com.example.Khabeer_task.di

import com.example.Khabeer_task.api.EmplyeeApi
import com.example.Khabeer_task.repository.EmployeeSalaryRepository
import com.example.Khabeer_task.repository.EmployeeSalaryRepositoryImplementation
import com.example.Khabeer_task.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun getApiInstance(): EmplyeeApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmplyeeApi::class.java)

    @Provides
    @Singleton
    fun getRepoInstance(api: EmplyeeApi): EmployeeSalaryRepository =
        EmployeeSalaryRepositoryImplementation(api)
}
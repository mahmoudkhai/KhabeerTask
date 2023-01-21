package com.example.Khabeer_task.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.Khabeer_task.model.ApiResponse
import com.example.Khabeer_task.model.EmployeeX
import com.example.Khabeer_task.util.NetworkResponse
import com.example.Khabeer_task.viewModel.EmployeeViewModel
import com.example.khabeer_task.R
import com.example.khabeer_task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val employeeViewModel: EmployeeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get navHostFragment and NavController.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController



}}
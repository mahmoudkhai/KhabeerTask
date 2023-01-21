package com.example.Khabeer_task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anychart.AnyChart
import com.example.Khabeer_task.model.ApiResponse
import com.example.Khabeer_task.model.EmployeeX
import com.example.Khabeer_task.util.NetworkResponse
import com.example.Khabeer_task.viewModel.EmployeeViewModel
import com.example.khabeer_task.databinding.FragmentEmployeeSalaryBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeSalary : Fragment() {
    private val employeeViewModel: EmployeeViewModel by viewModels()
    private lateinit var binding: FragmentEmployeeSalaryBinding
    val pie = AnyChart.pie()
    private lateinit var pieChart:PieChart
    val data: MutableList<PieEntry> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeSalaryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeViewModel.apiResponse.observe(requireActivity()) { response ->
            when (response) {
                is NetworkResponse.Loading -> {
                    showProgressBar()
                }
                is NetworkResponse.Error -> {
                    showErrorLayout(response)
                }
                is NetworkResponse.Success -> {

                    hideErrorAndProgressBar()
                    extractEmployeeInformation(response)

                }
            }
        }

    }

    private fun extractEmployeeInformation(response: NetworkResponse.Success<ApiResponse?>) {
        val employee = response.data?.Payroll?.Employee?.first()
        val hiringDate = response.data?.Payroll?.Date
        val allowences = response.data?.Payroll?.Allowences
        val deductions = response.data?.Payroll?.Deduction
        val deduction = deductions?.first()?.SAL_VALUE
        val majorSalary = allowences?.first()?.SAL_VALUE
        val workNatureValue = allowences?.get(1)?.SAL_VALUE
        val entitlements = majorSalary!!.plus(workNatureValue!!)
        val totalSalary: Double? =
            deduction?.minus(entitlements)

        val deductionPercentage = (deduction!! / (entitlements + deduction) )*100
        val entitlementsPercentage = (entitlements / (entitlements + deduction) )*100
        data.add(PieEntry( deductionPercentage.toFloat() , "deduction"))
        data.add(PieEntry( entitlementsPercentage.toFloat() , "entitlements"))
        val dataSet = PieDataSet(data , "Salary Statistics")
//        dataSet.setColors( , Color)
//        binding.salaryStatistics.setChart(pie)

        setViewsOfEmployeeSalaryFragment(
            employee,
            deduction,
            workNatureValue,
            majorSalary,
            totalSalary,
            hiringDate
        )
    }

    private fun setViewsOfEmployeeSalaryFragment(
        employee: EmployeeX?,
        deduction: Double?,
        workNatureValue: Double?,
        majorSalary: Double?,
        totalSalary: Double?,
        date: String?
    ) {
        binding.apply {
            employeeName.text = employee?.EMP_DATA_EN
            employeePosition.text = employee?.JOBNAME_EN
            employeeSalaryNumber.text =
                totalSalary.toString().plus(" ${employee?.CURRSYMBOL_EN}")
            hiringDate.text = date
            employeeEntitlementsNumber.text =
                majorSalary?.plus(workNatureValue!!).toString()
            employeeDeductionsNumber.text =
                deduction.toString().plus(" ${employee?.CURRSYMBOL_EN}")
            employeeTotalSalaryNumber.text =
                totalSalary.toString().plus(" ${employee?.CURRSYMBOL_EN}")
            basicSalary.text =
                majorSalary.toString().plus(" ${employee?.CURRSYMBOL_EN}")
            workNature.text =
                workNatureValue.toString().plus(" ${employee?.CURRSYMBOL_EN}")
            otherDeductions.text =
                deduction.toString().plus(" ${employee?.CURRSYMBOL_EN}")

        }
    }

    private fun showProgressBar() {
        binding.errorLayout.errorMessage.visibility = View.GONE
        binding.errorLayout.errorImage.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun hideErrorAndProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.errorMessage.visibility = View.GONE
        binding.errorLayout.errorImage.visibility = View.GONE
    }

    private fun showErrorLayout(response: NetworkResponse.Error) {
        binding.progressBar.visibility = View.GONE
        binding.errorLayout.errorMessage.visibility = View.VISIBLE
        binding.errorLayout.errorImage.visibility = View.VISIBLE
        binding.errorLayout.errorMessage.text = response.errorMessage
    }


}
package com.example.Khabeer_task.model

data class Payroll(
    val Allowences: List<Allowence>,
    val Date: String,
    val Deduction: List<Deduction>,
    val Employee: List<EmployeeX>
)
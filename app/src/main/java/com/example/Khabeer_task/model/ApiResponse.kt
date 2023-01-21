package com.example.Khabeer_task.model

data class ApiResponse(
    val Activation: Boolean,
    val ArabicMessage: String,
    val EnglishMessage: String,
    val Payroll: Payroll,
    val Success: Boolean
)
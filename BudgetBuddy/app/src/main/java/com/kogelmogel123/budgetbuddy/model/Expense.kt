package com.kogelmogel123.budgetbuddy.model

data class Expense(val name: String, val cost: Double = 0.00, val category: ExpenseCategory = ExpenseCategory.OTHER)
package com.kogelmogel123.budgetbuddy.repository

import com.kogelmogel123.budgetbuddy.model.Expense

interface ExpensesRepository {
    fun getExpenses(): List<Expense>
    //fun addExpense(expense: Expense)
}
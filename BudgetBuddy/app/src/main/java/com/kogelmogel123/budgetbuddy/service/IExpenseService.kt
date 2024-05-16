package com.kogelmogel123.budgetbuddy.service

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Expense
import java.time.LocalDate

interface IExpenseService {
    fun getAll(): LiveData<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    fun getExpenseById(id: Int): LiveData<Expense>
    fun getExpensesByLocalDate(currentDate: LocalDate): LiveData<List<Expense>>
}
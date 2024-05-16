package com.kogelmogel123.budgetbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpensesViewModel(private val expenseService: IExpenseService) : ViewModel() {
    private val currentDate = LocalDate.now()
    val expenses: LiveData<List<Expense>> = expenseService.getExpensesByLocalDate(currentDate)
    
    fun getTotalCost(): LiveData<Double> = expenses
        .map { expenses -> expenses.sumOf { it.cost } }

    fun getExpenseById(id: Int): LiveData<Expense> {
        return expenseService.getExpenseById(id)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expenseService.addExpense(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            expenseService.updateExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseService.deleteExpense(expense)
        }
    }
}
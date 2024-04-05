package com.kogelmogel123.budgetbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.data.ExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.launch

class ExpensesViewModel (private val expensesRepository: ExpensesRepository) : ViewModel() {

    val expenses: LiveData<List<Expense>> = expensesRepository.getAllExpenses()

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.insertExpense(expense)
        }
    }
}
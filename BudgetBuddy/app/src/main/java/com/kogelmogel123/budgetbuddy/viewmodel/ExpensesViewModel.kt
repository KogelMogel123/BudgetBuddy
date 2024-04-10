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

    fun getExpenseById(id: Int): LiveData<Expense?> {
        val result = MutableLiveData<Expense?>()
        viewModelScope.launch {
            val expense = expensesRepository.getExpenseById(id)
            result.postValue(expense)
        }
        return result
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.insertExpense(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.updateExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.deleteExpense(expense)
        }
    }
}
package com.kogelmogel123.budgetbuddy.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ExpensesViewModel(private val expensesRepository: IExpensesRepository) : ViewModel() {

    val expenses: LiveData<List<Expense>> = expensesRepository.getAllExpenses()

    fun getExpenseById(id: Int): LiveData<Expense> {
        return expensesRepository.getExpenseById(id)
            .catch { e ->
                Log.e("ExpensesViewModel", "Error fetching expense", e)
            }
            .asLiveData()
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
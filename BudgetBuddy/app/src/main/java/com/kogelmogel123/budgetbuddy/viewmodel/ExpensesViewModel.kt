package com.kogelmogel123.budgetbuddy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpensesViewModel(private val expensesRepository: IExpensesRepository) : ViewModel() {
    val currentDate = LocalDate.now()
    val dateRange = DateHelper.getMonthDateRange(currentDate.year, currentDate.month)
    val expenses: LiveData<List<Expense>> = expensesRepository.getByStartEndDate(dateRange.first, dateRange.second)

    fun getTotalCost(): LiveData<Double> = expensesRepository.getAll()
        .map { expenses -> expenses.sumOf { it.cost } }

    fun getExpenseById(id: Int): LiveData<Expense> {
        return expensesRepository.getById(id)
            .catch { e ->
                Log.e("ExpensesViewModel", "Error fetching expense", e)
            }
            .asLiveData()
    }

    fun getExpensesByCurrentMonth(): LiveData<List<Expense>> {
        val dateRange = DateHelper.getMonthDateRange(currentDate.year, currentDate.month)
        return expensesRepository.getByStartEndDate(dateRange.first, dateRange.second)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.insert(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.update(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.delete(expense)
        }
    }
}
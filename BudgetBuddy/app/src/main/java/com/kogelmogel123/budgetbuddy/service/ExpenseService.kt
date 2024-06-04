package com.kogelmogel123.budgetbuddy.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import kotlinx.coroutines.flow.catch
import java.time.LocalDate

class ExpenseService(private val expensesRepository: IExpensesRepository) : IExpenseService {

    override fun getAll(): LiveData<List<Expense>> {
        return expensesRepository.getAll()
    }
    
    override fun getExpenseById(id: Int): LiveData<Expense> {
        return expensesRepository.getById(id)
            .catch { e ->
                Log.e("ExpensesViewModel", "Error fetching expense", e)
            }
            .asLiveData()
    }

    override suspend fun addExpense(expense: Expense) {
            expensesRepository.insert(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
            expensesRepository.update(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
            expensesRepository.delete(expense)
    }

    override fun getExpensesByLocalDate(currentDate: LocalDate): LiveData<List<Expense>> {
        val dateRange = DateHelper.getMonthDateRange(currentDate.year, currentDate.month)
        return expensesRepository.getByStartEndDate(dateRange.first, dateRange.second)
    }

    override fun sumExpensesByCategory(expenses: List<Expense>, category: ExpenseCategory): Double {
        return expenses.filter { it.category == category }.sumOf { it.cost }
    }

    override fun sumCostExpenses(expenses: List<Expense>): Double {
        return expenses.sumOf { it.cost }
    }
}
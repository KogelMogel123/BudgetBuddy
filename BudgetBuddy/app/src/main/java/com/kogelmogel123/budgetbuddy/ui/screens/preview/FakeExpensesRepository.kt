package com.kogelmogel123.budgetbuddy.ui.screens.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kogelmogel123.budgetbuddy.data.ExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory

class FakeExpensesRepository : ExpensesRepository {
    private val expensesList = MutableLiveData<List<Expense>>(listOf(
        Expense(1, "Test Expense 1", 20.10, ExpenseCategory.FOOD,1),
        Expense(2, "Test Expense 2", 15.5, ExpenseCategory.ENTERTAINMENT,2),
        Expense(3, "Test Expense 3", 0.99, ExpenseCategory.CLOTHING,3)
    ))

    override fun getAllExpenses(): LiveData<List<Expense>> {
        return expensesList
    }

    override suspend fun insertExpense(expense: Expense) {
    }

    override suspend fun deleteExpense(expense: Expense) {
    }

    override suspend fun updateExpense(expense: Expense) {
    }
}
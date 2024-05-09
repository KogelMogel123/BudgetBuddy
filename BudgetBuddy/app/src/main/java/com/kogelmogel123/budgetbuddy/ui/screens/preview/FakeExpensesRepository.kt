package com.kogelmogel123.budgetbuddy.ui.screens.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeExpensesRepository : IExpensesRepository {
    private val expensesList = MutableLiveData<List<Expense>>(listOf(
        Expense(1, "Test Expense 1", 20.10, ExpenseCategory.FOOD),
        Expense(2, "Test Expense 2", 15.5, ExpenseCategory.ENTERTAINMENT),
        Expense(3, "Test Expense 3", 0.99, ExpenseCategory.CLOTHING)
    ))

    private val expensesFlow = flow {
        emit(Expense(1, "Test Expense 1", 20.10, ExpenseCategory.FOOD))
    }

    override fun getAllExpenses(): LiveData<List<Expense>> {
        return expensesList
    }

    override fun getExpenseById(id: Int): Flow<Expense> {
        return expensesFlow
    }

    override suspend fun insertExpense(expense: Expense) {
    }

    override suspend fun deleteExpense(expense: Expense) {
    }

    override suspend fun updateExpense(expense: Expense) {
    }
}
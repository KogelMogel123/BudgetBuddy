package com.kogelmogel123.budgetbuddy.ui.screens.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class FakeExpensesRepository : IExpensesRepository {
    private val expensesList = MutableLiveData<List<Expense>>(listOf(
        Expense(1, "Test Expense 1", 20.10, ExpenseCategory.GROCERIES, Date(), 1),
        Expense(2, "Test Expense 2", 15.5, ExpenseCategory.ENTERTAINMENT, Date(), 1),
        Expense(3, "Test Expense 3", 0.99, ExpenseCategory.OTHER, Date(), 1),
        Expense(4, "Test Expense 3", 4.0, ExpenseCategory.HOUSING, Date(), 1)
    ))

    private val expensesFlow = flow {
        emit(Expense(1, "Test Expense 1", 20.10, ExpenseCategory.GROCERIES, Date(), 1))
    }

    override fun getAll(): LiveData<List<Expense>> {
        return expensesList
    }

    override fun getByStartEndDate(startDate: Date, endDate: Date): LiveData<List<Expense>> {
        return expensesList
    }

    override fun getById(id: Int): Flow<Expense> {
        return expensesFlow
    }

    override suspend fun insert(expense: Expense) {
    }

    override suspend fun delete(expense: Expense) {
    }

    override suspend fun update(expense: Expense) {
    }
}
package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Expense

class OfflineExpensesRepository(private val expenseDao: ExpenseDao) : ExpensesRepository {
    override fun getAllExpenses(): LiveData<List<Expense>> = expenseDao.getAll()

    //override fun getItemStream(id: Int): Flow<Expense?> = expenseDao.getItem(id)

    override suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)
}

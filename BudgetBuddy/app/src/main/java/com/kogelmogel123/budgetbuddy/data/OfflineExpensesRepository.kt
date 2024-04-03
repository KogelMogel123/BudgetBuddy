package com.kogelmogel123.budgetbuddy.data

import ExpenseDao
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.data.ExpensesRepository

class OfflineExpensesRepository(private val expenseDao: ExpenseDao) : ExpensesRepository {
    //override fun getAllItemsStream(): Flow<List<Expense>> = expenseDao.getAllItems()

    //override fun getItemStream(id: Int): Flow<Expense?> = expenseDao.getItem(id)

    override suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)
}

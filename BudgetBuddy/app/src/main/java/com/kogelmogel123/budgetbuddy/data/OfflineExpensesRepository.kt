package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.flow.Flow
import java.util.Date

class OfflineExpensesRepository(private val expenseDao: IExpenseDao) : IExpensesRepository {
    override fun getAll(): LiveData<List<Expense>> = expenseDao.getAll()

    override fun getByStartEndDate(startDate: Date, endDate: Date): LiveData<List<Expense>> = expenseDao.getByStartEndDate(startDate, endDate)

    override fun getById(id: Int): Flow<Expense> = expenseDao.getById(id)

    override suspend fun insert(expense: Expense) = expenseDao.insert(expense)

    override suspend fun delete(expense: Expense) = expenseDao.delete(expense)

    override suspend fun update(expense: Expense) = expenseDao.update(expense)
}

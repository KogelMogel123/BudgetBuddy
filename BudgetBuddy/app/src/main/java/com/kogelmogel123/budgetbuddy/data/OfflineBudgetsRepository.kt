package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.MonthEnum
import kotlinx.coroutines.flow.Flow

class OfflineBudgetsRepository(private val budgetDao: IBudgetDao) : IBudgetsRepository {
    override fun getAll(): LiveData<List<Budget>> = budgetDao.getAll()

    override fun getById(id: Int): Flow<Budget> = budgetDao.getById(id)

    override fun getByDate(month: MonthEnum, year: Int): Flow<Budget> = budgetDao.getByDate(month, year)

    override suspend fun insert(budget: Budget) = budgetDao.insert(budget)

    override suspend fun delete(budget: Budget) = budgetDao.delete(budget)

    override suspend fun update(budget: Budget) = budgetDao.update(budget)
}
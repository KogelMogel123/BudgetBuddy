package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import kotlinx.coroutines.flow.Flow

class OfflineBudgetsRepository(private val budgetDao: IBudgetDao) : IBudgetsRepository {
    override fun getAllBudgets(): LiveData<List<Budget>> = budgetDao.getAll()

    override fun getBudgetById(id: Int): Flow<Budget> = budgetDao.getBudgetById(id)

    override suspend fun insertBudget(budget: Budget) = budgetDao.insert(budget)

    override suspend fun deleteBudget(budget: Budget) = budgetDao.delete(budget)

    override suspend fun updateBudget(budget: Budget) = budgetDao.update(budget)
}
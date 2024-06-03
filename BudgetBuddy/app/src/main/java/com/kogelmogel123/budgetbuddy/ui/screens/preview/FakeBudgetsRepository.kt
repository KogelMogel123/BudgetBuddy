package com.kogelmogel123.budgetbuddy.ui.screens.preview

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.BudgetWithExpenses
import kotlinx.coroutines.flow.Flow
import java.time.Month

class FakeBudgetsRepository: IBudgetsRepository {
    override fun getAll(): LiveData<List<Budget>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): Flow<Budget> {
        TODO("Not yet implemented")
    }

    override fun getByDate(month: Month, year: Int): Flow<Budget> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(budget: Budget) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(budget: Budget) {
        TODO("Not yet implemented")
    }

    override suspend fun update(budget: Budget) {
        TODO("Not yet implemented")
    }

    override fun getIdByDate(month: Month, year: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getBudgetWithExpenses(): LiveData<List<BudgetWithExpenses>> {
        TODO("Not yet implemented")
    }
}
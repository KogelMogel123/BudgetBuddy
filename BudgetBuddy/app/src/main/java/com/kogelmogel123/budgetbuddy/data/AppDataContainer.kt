package com.kogelmogel123.budgetbuddy.data

import android.content.Context

interface AppContainer {
    val expensesRepository: IExpensesRepository
    val budgetsRepository: IBudgetsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val expensesRepository: IExpensesRepository by lazy {
        OfflineExpensesRepository(BudgetBuddyDatabase.getDatabase(context).expenseDao())
    }

    override val budgetsRepository: IBudgetsRepository by lazy {
        OfflineBudgetsRepository(BudgetBuddyDatabase.getDatabase(context).budgetDao())
    }
}
package com.kogelmogel123.budgetbuddy.data

import android.content.Context

interface AppContainer {
    val itemsRepository: IExpensesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: IExpensesRepository by lazy {
        OfflineExpensesRepository(BudgetBuddyDatabase.getDatabase(context).expenseDao())
    }
}
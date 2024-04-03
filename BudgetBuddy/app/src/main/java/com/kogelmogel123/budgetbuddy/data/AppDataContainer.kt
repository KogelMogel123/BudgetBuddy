package com.kogelmogel123.budgetbuddy.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ExpensesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ExpensesRepository by lazy {
        OfflineExpensesRepository(BudgetBuddyDatabase.getDatabase(context).expenseDao())
    }
}
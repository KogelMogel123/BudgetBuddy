package com.kogelmogel123.budgetbuddy.data

import android.content.Context

interface AppContainer {
    val itemsRepository: UsersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: UsersRepository by lazy {
        OfflineUsersRepository(BudgetBuddyDatabase.getDatabase(context).userDao())
    }
}
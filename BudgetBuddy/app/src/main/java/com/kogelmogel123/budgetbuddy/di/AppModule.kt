package com.kogelmogel123.budgetbuddy.di

import com.kogelmogel123.budgetbuddy.data.BudgetBuddyDatabase
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.data.OfflineExpensesRepository
import com.kogelmogel123.budgetbuddy.viewmodel.CameraScreenViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.ReceiptsImagesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BudgetBuddyDatabase.getDatabase(androidContext()) }

    single { get<BudgetBuddyDatabase>().expenseDao() }

    single<IExpensesRepository> {
        OfflineExpensesRepository(expenseDao = get())
    }

    viewModel { ExpensesViewModel(get()) }
    viewModel { ReceiptsImagesViewModel() }
    viewModel { CameraScreenViewModel() }
}
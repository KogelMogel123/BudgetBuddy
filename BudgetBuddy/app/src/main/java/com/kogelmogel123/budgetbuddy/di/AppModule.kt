package com.kogelmogel123.budgetbuddy.di

import com.kogelmogel123.budgetbuddy.data.BudgetBuddyDatabase
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.data.OfflineBudgetsRepository
import com.kogelmogel123.budgetbuddy.data.OfflineExpensesRepository
import com.kogelmogel123.budgetbuddy.service.BudgetService
import com.kogelmogel123.budgetbuddy.service.CameraService
import com.kogelmogel123.budgetbuddy.service.ExpenseService
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.ICameraService
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.CameraViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.DashboardScreenViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import com.kogelmogel123.budgetbuddy.viewmodel.ReceiptAnalysisScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BudgetBuddyDatabase.getDatabase(androidContext()) }

    single { get<BudgetBuddyDatabase>().expenseDao() }
    single { get<BudgetBuddyDatabase>().budgetDao() }

    single<IExpensesRepository> {
        OfflineExpensesRepository(expenseDao = get())
    }

    single<IBudgetsRepository> {
        OfflineBudgetsRepository(budgetDao = get())
    }

    factory<ICameraService> { CameraService() }
    factory<IExpenseService> { ExpenseService(get()) }
    factory<IBudgetService> { BudgetService(get()) }

    viewModel { ExpensesViewModel(get()) }
    viewModel { ReceiptAnalysisScreenViewModel(get()) }
    viewModel { BudgetViewModel(get()) }
    viewModel { CameraViewModel(get()) }
    viewModel { DashboardScreenViewModel(get(), get()) }
}
package com.kogelmogel123.budgetbuddy.di

import com.kogelmogel123.budgetbuddy.data.BudgetBuddyDatabase
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.data.IUsersRepository
import com.kogelmogel123.budgetbuddy.data.OfflineBudgetsRepository
import com.kogelmogel123.budgetbuddy.data.OfflineExpensesRepository
import com.kogelmogel123.budgetbuddy.data.OfflineUsersRepository
import com.kogelmogel123.budgetbuddy.service.BudgetService
import com.kogelmogel123.budgetbuddy.service.CameraService
import com.kogelmogel123.budgetbuddy.service.ExpenseService
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.ICameraService
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import com.kogelmogel123.budgetbuddy.service.IUserService
import com.kogelmogel123.budgetbuddy.service.UserService
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
    single { get<BudgetBuddyDatabase>().userDao() }

    single<IExpensesRepository> {
        OfflineExpensesRepository(expenseDao = get())
    }

    single<IBudgetsRepository> {
        OfflineBudgetsRepository(budgetDao = get())
    }

    single<IUsersRepository> {
        OfflineUsersRepository(userDao = get())
    }

    factory<ICameraService> { CameraService() }
    factory<IExpenseService> { ExpenseService(get()) }
    factory<IBudgetService> { BudgetService(get()) }
    factory<IUserService> { UserService(get()) }

    viewModel { ExpensesViewModel(get()) }
    viewModel { ReceiptAnalysisScreenViewModel(get(), get()) }
    viewModel { BudgetViewModel(get()) }
    viewModel { CameraViewModel(get()) }
    viewModel { DashboardScreenViewModel(get(), get()) }
}
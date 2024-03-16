package com.kogelmogel123.budgetbuddy.di

import com.kogelmogel123.budgetbuddy.repository.ExpensesRepository
import com.kogelmogel123.budgetbuddy.repository.FakeExpensesRepository
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ExpensesRepository> { FakeExpensesRepository() }
    viewModel { ExpensesViewModel(get()) }
}
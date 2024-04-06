package com.kogelmogel123.budgetbuddy.ui.screens.preview

import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel

fun mockExpensesViewModel(): ExpensesViewModel {
    val fakeRepository = FakeExpensesRepository()
    return ExpensesViewModel(expensesRepository = fakeRepository)
}
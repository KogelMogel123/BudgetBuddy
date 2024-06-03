package com.kogelmogel123.budgetbuddy.ui.screens.preview

import com.kogelmogel123.budgetbuddy.service.BudgetService
import com.kogelmogel123.budgetbuddy.service.ExpenseService
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel

fun mockExpensesViewModel(): ExpensesViewModel {
    val fakeExpensesRepository = FakeExpensesRepository()
    val fakeBudgetsRepository = FakeBudgetsRepository()
    val fakeExpenseService = ExpenseService(fakeExpensesRepository)
    val fakeBudgetService = BudgetService(fakeBudgetsRepository)
    return ExpensesViewModel(fakeExpenseService, fakeBudgetService)
}
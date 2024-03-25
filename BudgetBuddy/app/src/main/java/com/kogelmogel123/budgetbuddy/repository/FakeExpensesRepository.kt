package com.kogelmogel123.budgetbuddy.repository

import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory

class FakeExpensesRepository : ExpensesRepository {
    override fun getExpenses() = listOf(
        Expense("Kawa", 5.99, ExpenseCategory.FOOD,1),
        Expense("Bilet autobusowy", 3.20, ExpenseCategory.TRANSPORT,1),
        Expense("Kanapka z szynką i serem", 8.00, ExpenseCategory.FOOD,1),
    )
}
package com.kogelmogel123.budgetbuddy.repository

import com.kogelmogel123.budgetbuddy.model.Expense

class FakeExpensesRepository : ExpensesRepository {
    override fun getExpenses() = listOf(
        Expense(1, "Kawa", 9.99),
        Expense(2, "Bilet autobusowy", 3.20),
        // Dodaj więcej przykładowych wydatków
    )

   // public fun addExpense(expense: Expense) {
   //     _expenses.value = _expenses.value?.plus(expense)
    //}
}
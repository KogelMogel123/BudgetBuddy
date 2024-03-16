package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItem
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel()) {
    //val expenses by viewModel.expenses

    viewModel.sayHello("Kogel Mogel")

   // LazyColumn {
    //    items(expenses, itemContent = { expense ->
    //        ExpenseItem(expense)
    //    })
    //}
}

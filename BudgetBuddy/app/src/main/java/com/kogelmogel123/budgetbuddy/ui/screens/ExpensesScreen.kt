package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItem
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.kogelmogel123.budgetbuddy.ui.components.FloatingActionButtonComponent

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel()) {

    val expenses by viewModel.expenses.observeAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButtonComponent(onFabClicked = { })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                expenses?.let {
                    items(it) { expense ->
                        ExpenseItem(expense)
                    }
                }
            }
        }
    }
}
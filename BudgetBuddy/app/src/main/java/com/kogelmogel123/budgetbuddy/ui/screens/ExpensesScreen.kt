package com.kogelmogel123.budgetbuddy.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItem
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.ui.components.FloatingActionButtonComponent
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockExpensesViewModel
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel(), navController: NavController) {

    val expenses by viewModel.expenses.observeAsState(initial = emptyList())

        Scaffold(
            floatingActionButton = {
                FloatingActionButtonComponent(onFabClicked = { navController.navigate("addExpense") })
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Text(text = stringResource(id = R.string.expenses), style = MaterialTheme.typography.titleLarge)
                LazyColumn {
                    expenses?.let {
                        items(it) { expense ->
                            ExpenseItem(expense,
                                onEdit = { navController.navigate("editExpense/${expense.id}") },
                                onDelete = { viewModel.deleteExpense(expense) })
                        }
                    }
                }
            }
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpensesScreenPreview() {
    ExpensesScreen(viewModel = mockExpensesViewModel(), navController = mockNavController())
}
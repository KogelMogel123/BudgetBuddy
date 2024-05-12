package com.kogelmogel123.budgetbuddy.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItemComponent
import com.kogelmogel123.budgetbuddy.ui.components.TripleFloatingActionButton
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockExpensesViewModel
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel(), navController: NavController) {

    val expenses by viewModel.expenses.observeAsState(initial = emptyList())
    val totalCost by viewModel.getTotalCost().observeAsState(0.0)

    Scaffold(
        floatingActionButton = {
            TripleFloatingActionButton(
                onFirstButtonClick = { navController.navigate("addExpenseScreen") },
                onSecondButtonClick = { navController.navigate("scanReceiptScreen") },
                onThirdButtonClick = { navController.navigate("receiptsAnalysisScreen") },
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_expense_screen),
                imageVector2 = Icons.Filled.Search,
                contentDescription2 = stringResource(id = R.string.scan_receipt_screen),
                imageVector3 = Icons.Filled.Image,
                contentDescription3 = stringResource(id = R.string.scan_receipt_screen)
            )
        }
    )
    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "${stringResource(id = R.string.total)}: ${String.format("%.2f", totalCost)} zł",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            LazyColumn {
                expenses?.let {
                    items(it) { expense ->
                        ExpenseItemComponent(expense,
                            onEdit = { navController.navigate("editExpenseScreen/${expense.id}") },
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
package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItem
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseCategorySelector

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel()) {

    val expenses by viewModel.expenses.observeAsState(initial = emptyList())
    var expenseName by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }

    Column {

        OutlinedTextField(
            value = expenseName,
            onValueChange = { expenseName = it },
            label = { Text("Expense name") },
        )

        OutlinedTextField(
            value = cost,
            onValueChange = { newValue ->
                val processedValue = newValue.replace(',', '.')

                if (processedValue.count { it == '.' } <= 1) {
                    val dotIndex = processedValue.indexOf('.')

                    if (dotIndex == -1 || processedValue.length - dotIndex - 1 <= 2) {
                        cost = processedValue
                    }
                }
            },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        ExpenseCategorySelector(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }

        Button(onClick = {
            viewModel.addExpense(
                Expense(
                    expenseName,
                    cost.toDouble(),
                    selectedCategory ?: ExpenseCategory.OTHER,1
                )
            )

            expenseName = ""
            cost = ""
            selectedCategory = null

        }) {
            Text(text = "Add Expense")
        }

        LazyColumn {
            items(expenses) { expense ->
                ExpenseItem(expense)
            }
        }
    }
}
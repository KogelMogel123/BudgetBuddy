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
import androidx.core.text.isDigitsOnly
import com.kogelmogel123.budgetbuddy.model.Expense

@Composable
fun ExpensesScreen(viewModel: ExpensesViewModel = koinViewModel()) {

    val expenses by viewModel.expenses.observeAsState(initial = emptyList())
    var expenseName by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }

    Column {

        OutlinedTextField(
            value = expenseName,
            onValueChange = { expenseName = it },
            label = { Text("Expense name") },
        )

        OutlinedTextField(
            value = cost,
            onValueChange = {
                val newText = it.replace(',', '.')
                if (newText.isValidMoneyAmount()) {
                    cost = newText.formatMoney()
                }
            },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Button(onClick = {
            viewModel.addExpense(Expense(0, expenseName, cost.toDoubleOrNull() ?: 0.0))
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

fun String.isValidMoneyAmount(): Boolean {
    return this.matches("-?\\d*(\\.\\d{0,2})?".toRegex())
}

fun String.formatMoney(): String {
    return try {
        val number = this.toDouble()
        String.format("%.2f", number)
    } catch (e: NumberFormatException) {
        this
    }
}

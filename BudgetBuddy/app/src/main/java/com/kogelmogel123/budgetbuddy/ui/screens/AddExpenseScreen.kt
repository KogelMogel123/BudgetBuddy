package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseCategorySelector
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockExpensesViewModel
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddExpenseScreen(viewModel: ExpensesViewModel = koinViewModel(), navController: NavController) {
    var expenseName by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }

    Column {

        OutlinedTextField(
            value = expenseName,
            onValueChange = { expenseName = it },
            label = { Text(stringResource(id = R.string.expense_name)) },
            modifier = Modifier.fillMaxWidth()
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
            label = { Text(stringResource(id = R.string.amount)) },
            modifier =Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        ExpenseCategorySelector(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }

        Button(onClick = {
            viewModel.addExpense(
                Expense(
                    0,
                    expenseName,
                    cost.toDouble(),
                    selectedCategory ?: ExpenseCategory.OTHER,1
                )
            )

            navController.navigate("expenses")
        },
            Modifier.padding(top = 16.dp).fillMaxWidth()){
            Text(text = stringResource(id = R.string.add_expense))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpenseScreenPreview() {
    AddExpenseScreen(viewModel = mockExpensesViewModel(), navController = mockNavController())
}
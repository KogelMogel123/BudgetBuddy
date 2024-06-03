package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseCategorySelectorComponent
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockExpensesViewModel
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@Composable
fun AddExpenseScreen(viewModel: ExpensesViewModel = koinViewModel(), navController: NavController) {
    var expenseName by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(4.dp)) {
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
                        errorMessage = null  // Clear error message when input is valid
                    }
                }
            },
            label = { Text(stringResource(id = R.string.amount)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = errorMessage != null
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        ExpenseCategorySelectorComponent(selectedCategory = selectedCategory) { category ->
            selectedCategory = category
        }

        Button(onClick = {
            val costValue = cost.toDoubleOrNull()
            if (costValue != null) {
                viewModel.addExpense(
                    Expense(
                        0,
                        expenseName,
                        costValue,
                        selectedCategory ?: ExpenseCategory.OTHER,
                        Date(),
                        1//TODO
                    )
                )
                navController.popBackStack()
            } else {
                errorMessage = context.getString(R.string.invalid_amount_error)
            }
        },
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = expenseName.isNotBlank() && cost.isNotBlank() && selectedCategory != null) {
            Text(text = stringResource(id = R.string.add_expense_screen))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpenseScreenPreview() {
    AddExpenseScreen(viewModel = mockExpensesViewModel(), navController = mockNavController())
}
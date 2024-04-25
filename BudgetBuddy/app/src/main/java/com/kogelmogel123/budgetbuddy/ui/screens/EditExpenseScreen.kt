package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseCategorySelector
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockExpensesViewModel
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController
import com.kogelmogel123.budgetbuddy.viewmodel.ExpensesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditExpenseScreen(viewModel: ExpensesViewModel = koinViewModel(), navController: NavController, id: Int) {
    val expenseLiveData = viewModel.getExpenseById(id)
    val expense by expenseLiveData.observeAsState()

    var expenseName by remember { mutableStateOf(expense?.name) }
    var cost by remember { mutableStateOf(expense?.cost.toString()) }
    var selectedCategory by remember { mutableStateOf<ExpenseCategory?>(expense?.category) }

    LaunchedEffect(expense) {
        expense?.let {
            expenseName = it.name
            cost = it.cost.toString()
            selectedCategory = it.category
        }
    }

    Column {

        OutlinedTextField(
            value = expenseName ?: "",
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
            viewModel.updateExpense(
                Expense(
                    id,
                    expenseName ?: "",
                    cost.toDouble(),
                    selectedCategory ?: ExpenseCategory.OTHER,1
                )
            )

            navController.navigate("expenses")
        },
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()){
            Text(text = stringResource(id = R.string.edit_expense))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditExpenseScreenPreview() {
    EditExpenseScreen(viewModel = mockExpensesViewModel(), navController = mockNavController(), id = 1)
}
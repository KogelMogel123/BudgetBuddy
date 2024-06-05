package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun AddBudgetScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController) {
    var amount by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(4.dp)) {
        OutlinedTextField(
            value = amount,
            onValueChange = { newValue ->
                val processedValue = newValue.replace(',', '.')

                if (processedValue.count { it == '.' } <= 1) {
                    val dotIndex = processedValue.indexOf('.')

                    if (dotIndex == -1 || processedValue.length - dotIndex - 1 <= 2) {
                        amount = processedValue
                        errorMessage = null  // Clear error message when input is valid
                    }
                }
            },
            label = { Text(stringResource(id = R.string.budget_amount)) },
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

        Button(onClick = {
            val amountValue = amount.toDoubleOrNull()
            if (amountValue != null && amountValue >= 0) {
                viewModel.addBudget(
                    Budget(
                        0,
                        LocalDate.now().month,
                        LocalDate.now().year,
                        amountValue
                    )
                )
                navController.popBackStack()
            } else {
                errorMessage = if (amountValue == null) {
                    context.getString(R.string.invalid_amount_error)
                } else {
                    context.getString(R.string.negative_amount_error)
                }
            }
        },
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = amount.isNotBlank()){
            Text(text = stringResource(id = R.string.set_a_budget))
        }
    }
}
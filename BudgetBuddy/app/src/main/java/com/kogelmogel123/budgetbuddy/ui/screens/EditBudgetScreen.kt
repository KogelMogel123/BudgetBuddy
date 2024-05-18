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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
fun EditBudgetScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController, id: Int) {
    val budgetLiveData = viewModel.getBudgetById(id)
    val budget by budgetLiveData.observeAsState()
    var amount by remember { mutableStateOf(budget?.amount.toString()) }

    LaunchedEffect(budget) {
        budget?.let {
            amount = it.amount.toString()
        }
    }

    Column(modifier = Modifier.padding(4.dp)) {

        OutlinedTextField(
            value = amount,
            onValueChange = { newValue ->
                val processedValue = newValue.replace(',', '.')

                if (processedValue.count { it == '.' } <= 1) {
                    val dotIndex = processedValue.indexOf('.')

                    if (dotIndex == -1 || processedValue.length - dotIndex - 1 <= 2) {
                        amount = processedValue
                    }
                }
            },
            label = { Text(stringResource(id = R.string.budget_amount)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Button(onClick = {
            viewModel.updateBudget(
                Budget(
                    id,
                    LocalDate.now().month,
                    LocalDate.now().year,
                    amount.toDouble()
                )
            )

            navController.popBackStack()
        },
            Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            enabled = amount.isNotBlank()){
            Text(text = stringResource(id = R.string.modify_the_budget))
        }
    }
}
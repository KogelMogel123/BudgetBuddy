package com.kogelmogel123.budgetbuddy.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.ui.components.ExpenseItemComponent
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun BudgetWithExpensesScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController, id: Int) {
    val context = LocalContext.current
    val budgetWithExpenses = viewModel.getBudgetWithExpensesById(id).observeAsState()
    val totalCost by remember(budgetWithExpenses) {
        derivedStateOf {
            budgetWithExpenses.value?.expenses?.let { viewModel.getTotalCost(it) } ?: 0.0
        }
    }

    Scaffold(
    )
    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(
                text = "${stringResource(id = R.string.total)}: ${String.format("%.2f", totalCost)} zł",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Text(text = "${stringResource(id = R.string.expenses_in_the_month_of)} ${budgetWithExpenses.value?.budget?.month?.let {
                DateHelper.getLocalizedName(context,
                    it
                )
            }}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            LazyColumn {
                budgetWithExpenses.value?.let {
                    items(it.expenses) { expense ->
                        ExpenseItemComponent(expense,
                            onEdit = { },
                            onDelete = { })
                    }
                }
            }
        }
    }
}
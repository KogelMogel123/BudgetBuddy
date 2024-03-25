package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.columnSeries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val modelProducer = remember { CartesianChartModelProducer.build() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Przykład Scaffold") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Tu umieść akcję dodawania nowego wpisu
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Dodaj")
            }
        },
        floatingActionButtonPosition = FabPosition.End, // Pozycjonowanie FAB w dolnym prawym rogu
        content = { padding ->
            // Główna treść aplikacji
            Text("Witaj w Jetpack Compose!", modifier = Modifier.padding(padding))
        }
    )

    Column {
        LaunchedEffect(Unit) { modelProducer.tryRunTransaction { columnSeries { series(4, 12, 8, 16) } } }
        CartesianChartHost(
            rememberCartesianChart(
                rememberColumnCartesianLayer(),
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
            ),
            modelProducer,
        )
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
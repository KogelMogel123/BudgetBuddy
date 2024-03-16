package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InformationScreen(onClick: (String) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    )
    {
        Text(text = "O projekcie", modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Aplikacja do zarządzania finansami osobistymi na platformie Android, stworzona w Kotlinie. Umożliwia użytkownikom skanowanie paragonów, automatyczne rozpoznawanie wydatków, zarządzanie budżetem, analizę wydatków. Celem projektu jest ułatwienie użytkownikom monitorowania ich wydatków i optymalizacja zarządzania finansami.", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
        Text(text = "Aplikacja tworzona w ramach #100commitow", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))

        HorizontalDivider()

        Text(text = "Wykorzystane Technologie", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Kotlin\nAndroid Studio", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))

        HorizontalDivider()

        Text(text = "MUST HAVE", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Skanowanie paragonów\nRozpoznawanie Paragonów (Komunikacja z AI)\nKategoryzacja zakupów\nMożliwość wpisania zakupów bez paragonu\nPodstawowy interfejs użytkownika", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))

        Text(text = "SHOULD HAVE", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Możliwość stawiania celów budżetowych\nStatystyki celów\nStatystyki wydatków\nDostosowanie do różnych rozmiarów ekranu\nErgonomiczny interfejs użytkownika", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))

        HorizontalDivider()

        Text(text = "COULD HAVE", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Statystyki cen produktów w czasie\nPostawienie API\nKomunikacja z API\nBaza danych\nLogowanie\nRejestracja", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))

        HorizontalDivider()

        Text(text = "WON'T HAVE", modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = MaterialTheme.typography.titleLarge)
        Text(text = "Personalizacja interfejsu użytkownika\nTworzenie grup\nZapraszanie innych użytkowników (np. rodzina, partner/ka)\nWspółdzielenie paragonów/wydatków z innymi użytkownikami w ramach grup\nRozpoznawanie wzorców w wydatkach użytkownika związanych z porami roku czy świętami i dostarczanie przewidywań budżetowych\nNotyfikacje dotyczące osiągnięcia celów finansowych", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
    }
}
package com.kogelmogel123.budgetbuddy.ui.components

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.components.Legends
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import co.yml.charts.ui.piechart.utils.proportion

@ExperimentalMaterialApi
@Composable
fun SimpleDonutChart(context: Context, chartData: PieChartData) {
    val accessibilitySheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val sumOfValues = chartData.totalLength

    val proportions = chartData.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            strokeWidth = 200f,
            labelColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            labelFontSize = 22.sp,
            isSumVisible = true,
            sumUnit = " zł",
            showSliceLabels = true,
            labelType = PieChartConfig.LabelType.VALUE,
            isClickOnSliceEnabled = true
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = chartData, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            chartData,
            pieChartConfig
        ) { slice ->
            Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
        }
    }
}
package project.app.sale_crm.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AnalyticsPage(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Analytics Dashboard",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        // Monthly Sales Overview
        AnalyticsSection(
            title = "Monthly Sales Overview",
            description = "This chart shows the sales performance over the past six months. A steady increase indicates a positive trend.",
            chart = {
                CustomLineChart(
                    modifier = Modifier
                        .weight(0.5f)  // Occupy 50% of width
                        .height(300.dp),
                    chartData = ChartData(
                        points = listOf(
                            Point(0f, 40f),  // January
                            Point(1f, 90f),  // February
                            Point(2f, 20f),  // March
                            Point(3f, 60f),  // April
                            Point(4f, 80f),  // May
                            Point(5f, 70f)   // June
                        ),
                        backgroundColor = Color.Black,
                        lineStyle = LineStyle(color = Color.Blue)
                    ),
                    xAxisData = AxisData(
                        steps = 5,
                        backgroundColor = Color.Black,
                        labelFormatter = { it.toString() },
                    ),
                    yAxisData = AxisData(
                        steps = 10,
                        backgroundColor = Color.Black,
                        labelFormatter = { "${(it * 10)}%" }
                    )
                )
            }
        )

        // Sales Distribution by Region
        AnalyticsSection(
            title = "Sales Distribution by Region",
            description = "The sales distribution reveals key markets. Notice the regions with the highest sales for strategic focus.",
            chart = {
                CustomBarChart(
                    modifier = Modifier
                        .weight(0.5f)  // Occupy 50% of width
                        .height(350.dp),
                    barData = listOf(
                        BarData(Point(x = 0f, 40f), Color(0xFF4CAF50)),
                        BarData(Point(x = 1f, 90f), Color(0xFFFF9800)),
                        BarData(Point(x = 2f, 20f), Color(0xFFE91E63)),
                        BarData(Point(x = 3f, 60f), Color(0xFF00BCD4)),
                        BarData(Point(x = 4f, 80f), Color(0xFFCDDC39)),
                        BarData(Point(x = 5f, 70f), Color(0xFF673AB7))
                    ),
                    maxRange = 100,
                    xAxisConfig = AxisConfig(
                        5,
                        axisStepSize = 30.dp,
                        backgroundColor = Color.Black,
                        bottomPadding = 40.dp,
                        axisLabelAngle = 20f,
                        labelTextColor = Color.Gray
                    ),
                    yAxisConfig = AxisConfig(
                        steps = 5,
                        labelAndAxisLinePadding = 20.dp,
                        axisOffset = 20.dp,
                        labelTextColor = Color.Gray
                    ),
                    chartConfig = BarChartConfig(
                        paddingBetweenBars = 20.dp,
                        barWidth = 25.dp
                    ),
                    isHorizontal = false
                )
            }
        )

        // Monthly Targets
        AnalyticsSection(
            title = "Monthly Targets",
            description = "The pie chart shows the target achievements for this month. Focus on increasing the 'Pending' slice.",
            chart = {
                CustomPieChart(
                    modifier = Modifier
                        .weight(0.5f)  // Occupy 50% of width
                        .height(400.dp),
                    slices = listOf(
                        CustomPieSlice("Achieved", 70f, Color(0xFF4CAF50)),
                        CustomPieSlice("Pending", 30f, Color(0xFFE91E63))
                    ),
                    config = CustomPieChartConfig(
                        percentVisible = true,
                        isAnimationEnabled = true,
                        showSliceLabels = true,
                    )
                )
            }
        )

        // Customer Retention Rate
        AnalyticsSection(
            title = "Customer Retention Rate",
            description = "This chart tracks retention over the past five months. A higher percentage indicates better customer satisfaction.",
            chart = {
                CustomLineChart(
                    modifier = Modifier
                        .weight(0.5f)  // Occupy 50% of width
                        .height(300.dp),
                    chartData = ChartData(
                        points = listOf(
                            Point(0f, 80f),  // Month 1
                            Point(1f, 85f),  // Month 2
                            Point(2f, 78f),  // Month 3
                            Point(3f, 90f),  // Month 4
                            Point(4f, 88f)   // Month 5
                        ),
                        backgroundColor = Color.Black,
                        lineStyle = LineStyle(color = Color.Magenta)
                    ),
                    xAxisData = AxisData(
                        steps = 4,
                        backgroundColor = Color.Black,
                        labelFormatter = { "M${it + 1}" },
                    ),
                    yAxisData = AxisData(
                        steps = 10,
                        backgroundColor = Color.Black,
                        labelFormatter = { "${it * 10}%" }
                    )
                )
            }
        )

        // Lead Conversion Rate
        AnalyticsSection(
            title = "Lead Conversion Rate",
            description = "The pie chart illustrates the percentage of leads converted. Focus on strategies to increase the 'Converted' slice.",
            chart = {
                CustomPieChart(
                    modifier = Modifier
                        .weight(0.5f)  // Occupy 50% of width
                        .height(400.dp),
                    slices = listOf(
                        CustomPieSlice("Converted", 55f, Color(0xFF2196F3)),
                        CustomPieSlice("Not Converted", 45f, Color(0xFFFFC107))
                    ),
                    config = CustomPieChartConfig(
                        percentVisible = true,
                        isAnimationEnabled = true,
                        showSliceLabels = true,
                    )
                )
            }
        )
    }
}

@Composable
fun AnalyticsSection(
    title: String,
    description: String,
    chart: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Text Section
        Column(
            modifier = Modifier.weight(0.3f),  // Occupy 50% of width
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        // Chart Section
        Box(modifier = Modifier.weight(0.7f)) {
            chart()
        }
    }
}



@Composable
fun CustomLineChart(
    modifier: Modifier = Modifier,
    chartData: ChartData,
    xAxisData: project.app.sale_crm.analytics.AxisData = AxisData(),
    yAxisData: project.app.sale_crm.analytics.AxisData = AxisData(),
) {
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = chartData.points,
                    lineStyle = chartData.lineStyle,
                    intersectionPoint = chartData.intersectionPoint,
                    selectionHighlightPoint = chartData.selectionHighlightPoint,
                    shadowUnderLine = chartData.shadowUnderLine,
                    selectionHighlightPopUp = chartData.selectionHighlightPopUp
                )
            ),
        ),
        xAxisData = xAxisData.toInternalAxisData(chartData.points.size - 1),
        yAxisData = yAxisData.toInternalAxisData(),
        gridLines = GridLines(),
        backgroundColor = chartData.backgroundColor
    )

    LineChart(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(chartData.backgroundColor),
        lineChartData = lineChartData
    )
}

data class ChartData(
    val points: List<Point>,
    val backgroundColor: Color = Color.Black,
    val lineStyle: LineStyle = LineStyle(),
    val intersectionPoint: IntersectionPoint = IntersectionPoint(),
    val selectionHighlightPoint: SelectionHighlightPoint = SelectionHighlightPoint(),
    val shadowUnderLine: ShadowUnderLine = ShadowUnderLine(),
    val selectionHighlightPopUp: SelectionHighlightPopUp = SelectionHighlightPopUp()
)

data class AxisData(
    val steps: Int = 5,
    val axisStepSize: Dp = 100.dp,
    val backgroundColor: Color = Color.Gray,
    val labelAndAxisLinePadding: Dp = 15.dp,
    val labelFormatter: (Int) -> String = { it.toString() }
) {
    fun toInternalAxisData(steps: Int = this.steps): AxisData {
        return AxisData.Builder()
            .steps(steps)
            .axisStepSize(axisStepSize)
            .backgroundColor(backgroundColor)
            .labelData(labelFormatter)
            .labelAndAxisLinePadding(labelAndAxisLinePadding)
            .axisLabelColor(Color.LightGray)
            .build()
    }
}

@Composable
fun CustomBarChart(
    modifier: Modifier = Modifier,
    barData: List<BarData>,
    maxRange: Int,
    xAxisConfig: AxisConfig = AxisConfig(),
    yAxisConfig: AxisConfig = AxisConfig(),
    chartConfig: BarChartConfig = BarChartConfig(),
    isHorizontal: Boolean = false
) {
    val xAxisData = xAxisConfig.toInternalAxisData(barData.size - 1) { index -> barData[index].label }
    val yAxisData = yAxisConfig.toInternalAxisData(steps = chartConfig.yStepSize) { index ->
        (index * (maxRange / chartConfig.yStepSize)).toString()
    }

    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = Color.Black
    )

    BarChart(
        modifier = modifier.height(350.dp).background(Color.Black),
        barChartData = barChartData
    )
}

// Configuration classes for Axis and Chart settings

data class AxisConfig(
    val steps: Int = 5,
    val axisStepSize: Dp = 30.dp,
    val backgroundColor: Color = Color.Black,
    val labelAndAxisLinePadding: Dp = 20.dp,
    val bottomPadding: Dp = 40.dp,
    val labelTextColor: Color = Color.Gray,
    val axisOffset: Dp = 0.dp,
    val axisLabelAngle: Float = 0f
) {
    fun toInternalAxisData(steps: Int, labelDataProvider: (Int) -> String): AxisData {
        return AxisData.Builder()
            .steps(steps)
            .axisStepSize(axisStepSize)
            .backgroundColor(backgroundColor)
            .labelData(labelDataProvider)
            .labelAndAxisLinePadding(labelAndAxisLinePadding)
            .axisOffset(axisOffset)
            .axisLabelAngle(axisLabelAngle)
            .axisLabelColor(labelTextColor)
            .bottomPadding(bottomPadding)
            .build()
    }
}

data class BarChartConfig(
    val paddingBetweenBars: Dp = 20.dp,
    val barWidth: Dp = 25.dp,
    val yStepSize: Int = 10,
    val backgroundColor: Color = Color.Black
)

data class BarData(
    val label: String,
    val value: Float,
    val color: Color = Color.Blue
)

data class CustomPieSlice(
    val label: String,
    val value: Float,
    val color: Color
)

data class CustomPieChartConfig(
    val percentVisible: Boolean = true,
    val isAnimationEnabled: Boolean = true,
    val showSliceLabels: Boolean = false,
    val animationDuration: Int = 1500,
    val backgroundColor: Color = Color.Black, // Default background color is black
    val labelColor: Color = Color.Gray // Default label color is gray
)

@Composable
fun CustomPieChart(
    modifier: Modifier = Modifier,
    slices: List<CustomPieSlice>,
    config: CustomPieChartConfig = CustomPieChartConfig()
) {
    val pieChartData = PieChartData(
        slices = slices.map { slice ->
            PieChartData.Slice(slice.label, slice.value, slice.color)
        },
        plotType = PlotType.Pie,
    )

    val pieChartConfig = PieChartConfig(
        isAnimationEnable = config.isAnimationEnabled,
        showSliceLabels = config.showSliceLabels,
        animationDuration = config.animationDuration,
        backgroundColor = Color.Black
    )

    Box(
        modifier = modifier
            .background(config.backgroundColor)
            .padding(16.dp)
            .size(400.dp)
    ) {
        PieChart(
            modifier = Modifier
                .fillMaxSize(),
            pieChartData = pieChartData,
            pieChartConfig = pieChartConfig
        )
    }
}


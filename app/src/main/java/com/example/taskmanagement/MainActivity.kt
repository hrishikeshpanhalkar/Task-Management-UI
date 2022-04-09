package com.example.taskmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.taskmanagement.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackGroundColor
                ) {
                    window.statusBarColor = ContextCompat.getColor(this, R.color.primary)

                    Column(

                    ) {
                        HeaderUI()
                        TaskCardUI()
                        StatisticsUI()
                        DescriptionUI()
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionUI(){
    Column(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Make a youtube video on Task management app user interface and make sure to post" +
                    "it on youtube, also create thumbnail and other pages",
            fontFamily = Poppins,
            fontSize = 11.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun StatisticsUI() {
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Text(
            text = "Jetpack Compose UI Design",
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = PrimaryTextColor,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    tint = Color(0xFF818181),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "09.00 AM - 11.00 AM",
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    color = Color(0xFF818181),
                    fontWeight = FontWeight.Medium
                )

                Box(
                    modifier = Modifier
                        .clip(Shapes.large)
                        .background(Color(0xFFE1E3FA))
                        .padding(horizontal = 10.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "On Going",
                        fontSize = 10.sp,
                        fontFamily = Poppins,
                        color = Color(0xFF788589)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Statistics",
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = SubTextColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            StatisticsProgressUI()
            Spacer(modifier = Modifier.width(12.dp))
            StatisticIndicatorUI()
        }
    }
}

@Composable
fun StatisticIndicatorUI(){
    Column(
        modifier = Modifier
            .height(120.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IndicatorItemUI(text = "Finish on time")
        IndicatorItemUI(text = "Past the deadline", color = SecondaryColor)
        IndicatorItemUI(text = "Still ongoing", color = Color(0xFFE3E5E7))
    }
}

@Composable
fun IndicatorItemUI(text:String, color:Color = PrimaryColor){
    Row{
        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            fontFamily = Poppins,
            fontSize = 12.sp,
            color = Color(0xFF818181),
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun StatisticsProgressUI(primaryProgress: Float = 60f, secondaryProgress: Float = 15f) {
    Box(
        modifier = Modifier
            .size(120.dp),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier.size(100.dp),
        ) {
            drawCircle(
                SolidColor(Color(0xFFE3E3E7)),
                size.width / 2,
                style = Stroke(34f)
            )

            val convertedPrimaryValue = (primaryProgress / 100) * 360
            val convertedSecondaryValue = ((secondaryProgress / 100) * 360) + convertedPrimaryValue

            drawArc(
                brush = SolidColor(SecondaryColor),
                startAngle = -90f,
                sweepAngle = convertedSecondaryValue,
                useCenter = false,
                style = Stroke(34f, cap = StrokeCap.Round)
            )

            drawArc(
                brush = SolidColor(PrimaryColor),
                startAngle = -90f,
                sweepAngle = convertedPrimaryValue,
                useCenter = false,
                style = Stroke(34f, cap = StrokeCap.Round)
            )
        }
        val annotatedString = AnnotatedString.Builder(
            "${(primaryProgress + secondaryProgress).toInt()}%\nDone"
        )
            .apply {
                addStyle(
                    SpanStyle(
                        color = SubTextColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                    ), 4, 8
                )
            }

        Text(
            text = annotatedString.toAnnotatedString(),
            fontFamily = Poppins,
            fontSize = 20.sp,
            color = PrimaryTextColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskCardUI() {
    val annotatedString = AnnotatedString.Builder("4/6 Task")
        .apply {
            addStyle(
                SpanStyle(
                    color = PrimaryColor
                ), 0, 3
            )
        }
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
            .padding(top = 40.dp),
        elevation = 0.dp,
        shape = Shapes.large
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Daily Task",
                    fontFamily = Poppins,
                    fontSize = 12.sp,
                    color = SubTextColor,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tick_circle),
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = annotatedString.toAnnotatedString(),
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        color = PrimaryTextColor,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Almost finished, \n keep it up",
                    fontFamily = Poppins,
                    fontSize = 13.sp,
                    color = Color(0xFF292D32),
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.clip(Shapes.large),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryColor
                    ),
                    contentPadding = PaddingValues(vertical = 0.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "Daily Task",
                        fontSize = 10.sp,
                        modifier = Modifier.align(alignment = CenterVertically),
                        fontFamily = Poppins,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            ProgressUI(progress = 67f)
        }
    }
}

@Composable
fun ProgressUI(progress: Float) {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .padding(6.dp)
        ) {
            drawCircle(
                SolidColor(Color(0xFFE3E5E7)),
                size.width / 2,
                style = Stroke(26f)
            )
            val converted = (progress / 100) * 360
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(SecondaryColor, PrimaryColor)
                ),
                startAngle = -90f,
                sweepAngle = converted,
                useCenter = false,
                style = Stroke(26f, cap = StrokeCap.Round)
            )
        }

        val annotatedProgressString = AnnotatedString.Builder(
            "${progress.toInt()}%\nDone"
        )
            .apply {
                addStyle(
                    SpanStyle(
                        color = SubTextColor,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Normal
                    ), 4, 8
                )
            }

        Text(
            text = annotatedProgressString.toAnnotatedString(),
            fontFamily = Poppins,
            fontSize = 14.sp,
            color = PrimaryTextColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HeaderUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello, UI Stack",
                fontFamily = Poppins,
                fontSize = 18.sp,
                color = PrimaryTextColor,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Let's do your today task",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = SubTextColor,
                fontFamily = Poppins
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    }
}

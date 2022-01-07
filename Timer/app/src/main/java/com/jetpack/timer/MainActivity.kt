package com.jetpack.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.timer.ui.theme.TimerTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    strokeColor: Color,
    strokeWidth: Dp = 2.dp,
    totalTime: Long = 100000L
){

    var isTimerRunning = false

    var value by remember{
        mutableStateOf(1f)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning ){
        delay(100L)
        currentTime -= 100L
        value = (currentTime/totalTime).toFloat()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF101010)),
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = modifier
            .padding(10.dp)
            .size(100.dp)){
            drawArc(
                color = Color.Gray,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width.toFloat(),size.height.toFloat())
            )
            drawArc(
                color = Color.Green,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width.toFloat(),size.height.toFloat())
            )
            val center = Offset(size.width / 2f,size.height / 2f)
            val beta = (250f * value + 145f) * (PI/180f).toFloat() //converting degrees into radian
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a,center.y + b)),
                pointMode = PointMode.Points,
                color = Color.Green,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(text = currentTime.toString(), fontSize = 20.sp, color = Color.Green)
        OutlinedButton(
            onClick = {
                   isTimerRunning = true
            },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp, 15.dp)
                .background(Color.Green)
        ) {
            Text(text = "Start Timer",color = Color.White)
        }
    }

}
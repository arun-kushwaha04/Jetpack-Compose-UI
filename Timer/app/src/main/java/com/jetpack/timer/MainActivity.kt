package com.jetpack.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
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
                Surface(color =Color(0xFF101010), modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Timer(modifier = Modifier.size(200.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 5.dp,
    totalTime: Long = 10000L
){

    var size by remember{
        mutableStateOf(IntSize.Zero)
    }

    var isTimerRunning by remember{
        mutableStateOf(false)
    }

    var value by remember{
        mutableStateOf(1f)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning ){
        if(isTimerRunning && currentTime >= 0 ){
            delay(100L)
            currentTime -= 100L
            value = currentTime/totalTime.toFloat()
        }
        else if(currentTime < 0L){
            isTimerRunning = false
            currentTime = 0L
        }
    }

    Box(
        modifier = modifier
            .onSizeChanged {
                size = it
            },
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize(),
        ){
            drawArc(
                color = Color.Gray,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width.toFloat(),size.height.toFloat()),
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
            val beta = (250f * value + 145f ) * (PI/180f).toFloat() //converting degrees into radian
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
        Text(text = (currentTime/1000F).toInt().toString(), fontSize = 20.sp, color = Color.Green)
        Button(
            onClick = {
                if(currentTime == 0L){
                    currentTime = totalTime
                    value = 1f
                    isTimerRunning = false
                }
                else{
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp, 15.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(!isTimerRunning) Color.Green else Color.Red
            )

        ) {
            Text(
                text = if(currentTime == totalTime && !isTimerRunning) "Start Timer" else if(currentTime == 0L) "Reset Timer" else if(isTimerRunning) "Stop Timer" else "Resume Timer",
                modifier = Modifier.background(Color.Transparent),
                color = Color.White
            )
        }
    }
}
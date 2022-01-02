package com.jetpack.volumecontroller

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.tanh

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize().background(Color(0XFF101010)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Green, RoundedCornerShape(10.dp))
                        .padding(30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    var percentage by remember {
                        mutableStateOf(0f)
                    }
                    val volumeBars = 20
                    MusicKnob(
                        modifier = Modifier.size(100.dp)
                    ){
                        percentage = it
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    VolumeBar(
                        activeBars = (percentage * volumeBars).toInt(),
                        modifier = Modifier.fillMaxWidth().height(30.dp),
                        volumeBars = volumeBars
                    )
                }
            }
        }
    }
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    volumeBars: Int,
    activeBars: Int,
){
    BoxWithConstraints(
        modifier = modifier
    ) {

        val barWidth = remember {
         constraints.maxWidth / (2f * volumeBars)
        }

        Canvas(modifier = Modifier){
            for (i in 0 until volumeBars){
                drawRoundRect(
                    color = if (i < activeBars) Color.Green else Color.Gray,
                    topLeft = Offset(i * barWidth * 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(10F)
                )
            }
        }
    }

}


@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onValueChange :(Float) -> Unit
){
    var rotation by remember {
        mutableStateOf(limitAngle)
    }

    var rotateX by remember {
        mutableStateOf(0F)
    }

    var rotateY by remember {
        mutableStateOf(0F)
    }

    var centerX by remember {
        mutableStateOf(0F)
    }

    var centerY by remember {
        mutableStateOf(0F)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2F
                centerY = windowBounds.size.height / 2F
            }
            .pointerInteropFilter { event ->
                rotateX = event.x
                rotateY = event.y
                var angle = -atan2(-rotateX + centerX, -rotateY + centerY) * (180f / PI).toFloat()
                if (angle < 0) {
                    angle += 360f
                }
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle < limitAngle) {
                            angle = limitAngle
                        }
                        if (angle > 360f - limitAngle) {
                            angle = 360f - limitAngle
                        }
                        rotation = angle
                        val percentage = (angle - limitAngle) / (360f - 2 * limitAngle)
                        onValueChange(percentage)
                        true
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )

}
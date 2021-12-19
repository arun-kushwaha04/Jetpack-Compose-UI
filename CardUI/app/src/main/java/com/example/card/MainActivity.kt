package com.example.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.hogwart)
            val title = "Hogwarts School of Witchcraft and Wizardry"
            val description = "Hogwarts School of Witchcraft and Wizardry"

            Box(modifier = Modifier.fillMaxSize().padding(10.dp)){
                ImageBox(painter = painter, contentDescription = description, title = title)
            }


        }
    }
}

@Composable
fun ImageBox(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .height(250.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop)
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 200f
                        )
                    )
            )
            Box(modifier = modifier
                .fillMaxSize()
                .padding(10.dp), contentAlignment = Alignment.BottomCenter) {
                Text(text = title, style = TextStyle(color = Color.White, fontSize = 20.sp))
            }
        }
    }
}

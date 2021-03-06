package com.jetpack.meditationui.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.meditationui.R
import com.jetpack.meditationui.ui.theme.*
import com.plcoding.meditationuiyoutube.BottomMenuContent
import com.plcoding.meditationuiyoutube.Feature
import com.plcoding.meditationuiyoutube.standardQuadFromTo

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){
        Column {
            GreetMessage()
            ChipsRow(chips = listOf("Happy Mood", "Sad Mood", "Relaxing Mood"))
            CurrentlyPlaying()
            SongCategories(
                categories = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ),modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun GreetMessage(
    personName: String = "Arun"
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text ="Good Morning, $personName",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "We wish you a wonderful day",
                style = MaterialTheme.typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Button",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipsRow (
    chips: List<String>
){

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow{
        items(chips.size){
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 15.dp, top = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue)
                    .clickable { selectedChipIndex = it }
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentlyPlaying(
    color: Color = LightRed
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text ="Imagine Dragons",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Sad . 2 min",
                style = MaterialTheme.typography.body1
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "play song",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun SongCategories(
    categories:List<Feature>
){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Categories",
            color = TextWhite,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(categories.size){
                CategoryItem(category = categories[it])
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Feature
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(category.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium Colored Path
        val mediumColoredPoint1 = Offset(0f,height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f,height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f,height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f,height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.5f,-height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)
            lineTo(width.toFloat()+ 100f, height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }

        //Light Colored Path
        val lightColoredPoint1 = Offset(0f,height * 0.35f)
        val lightColoredPoint2 = Offset(width * 0.1f,height * 0.4f)
        val lightColoredPoint3 = Offset(width * 0.4f,height * 0.08f)
        val lightColoredPoint4 = Offset(width * 0.75f,height * 0.9f)
        val lightColoredPoint5 = Offset(width * 1.5f,-height.toFloat())

        val lightColoredPath = Path().apply {
            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
            standardQuadFromTo(lightColoredPoint1,lightColoredPoint2)
            standardQuadFromTo(lightColoredPoint2,lightColoredPoint3)
            standardQuadFromTo(lightColoredPoint3,lightColoredPoint4)
            standardQuadFromTo(lightColoredPoint4,lightColoredPoint5)
            lineTo(width.toFloat()+ 100f, height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()){
            drawPath(
                path = mediumColoredPath,
                color = category.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = category.lightColor
            )
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ){
            Text(
                text = category.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .align(Alignment.TopStart),
                lineHeight = 26.sp
            )
            Icon(
                painter = painterResource(id = category.iconId),
                contentDescription = category.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Play",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        //do something here
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

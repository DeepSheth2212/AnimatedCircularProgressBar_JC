package com.example.animatedcircularprogressbar_jc


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize()
            , contentAlignment = Alignment.Center
            ){
                circularProgressBar(percentage = 0.8f, number = 100)

            }

        }
    }
}

@Composable
fun circularProgressBar(
    percentage : Float,
    number : Int,
    fontSize : TextUnit = 28.sp,
    radius: Dp= 50.dp,
    color : Color = Color.Blue,
    strokeWidth : Dp = 8.dp,
    animDuration : Int  = 1000,
    animDelay : Int = 0,
){
    var animationPlayed by remember{
        mutableStateOf(false)//false bcz initially animations are not played
    } // tells whether animations are already played or not

    val currentPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis =  animDelay
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed=true
    }//so it gets triggered only first time the composable creates

    Box(modifier = Modifier.size(radius*2),
    contentAlignment = Alignment.Center){
        Canvas(modifier = Modifier.size(radius*2)){
            drawArc(
                color,-90f,360*currentPercentage.value,false,
                style = Stroke(strokeWidth.toPx() , cap = StrokeCap.Round)
            )
        }
        
        Text(
            text = ((currentPercentage.value*number).toInt()).toString()
            , color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
        )
        
    }




}


package com.example.firstwatchapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText

@Composable
fun HeartRateScreen(heartRate : Float) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    )
    {
        TimeText()
        if (heartRate != 0f){
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = "Fréquence cardiaque :",
                    style = MaterialTheme.typography.title3
                )
                Text(
                    text = "$heartRate bpm",
                    style = MaterialTheme.typography.title1
                )
            }
        }
        else
        {
            Text(
                text = "Acquiring heart rate ...",
                style = MaterialTheme.typography.title3
            )
        }
    }
}
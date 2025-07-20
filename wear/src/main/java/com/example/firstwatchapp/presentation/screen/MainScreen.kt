package com.example.firstwatchapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText


@Composable
fun MainScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        TimeText()
        Button(
            shape = RoundedCornerShape(12),
            onClick = { navController.navigate("heart_rate")},
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .padding(horizontal = 12.dp, vertical = 6.dp)

        ) {
            Text("Démarrer", softWrap = false)
        }

    }
}
package com.example.firstwatchapp.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Colors
import androidx.compose.ui.graphics.Color

private val OnPrimaryColor = Color(0xFF4A3F72)
private val PurplePrimary = Color(0xFF7E57C2)
private val PurpleOnPrimary = Color(0xFFEDE7F6)
private val PurplePrimaryContainer = Color(0xFF512DA8)
private val PurpleOnPrimaryContainer = Color(0xFFD1C4E9)

private val PurpleSecondary = Color(0xFF9575CD)
private val PurpleOnSecondary = Color(0xFFEDE7F6)
private val PurpleSecondaryContainer = Color(0xFF673AB7)
private val PurpleOnSecondaryContainer = Color(0xFFD1C4E9)

private val BackgroundColor = Color.Black
private val SurfaceColor = Color(0xFF1C1B1F)
private val OnSurfaceColor = Color(0xFFE0E0E0)

private val pastelColorPalette = Colors(
    primary = PurplePrimary,
    onPrimary = PurpleOnPrimary,
    
    secondary = PurpleSecondary,
    onSecondary = PurpleOnSecondary,
    
    background = BackgroundColor,
    onBackground = OnSurfaceColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    
    error = Color(0xFFCF6679),
    onError = Color.White,
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    /**
     * Empty theme to customize for your app.
     * See: https://developer.android.com/jetpack/compose/designsystems/custom
     */
    MaterialTheme(
        content = content,
        colors = pastelColorPalette
    )
}
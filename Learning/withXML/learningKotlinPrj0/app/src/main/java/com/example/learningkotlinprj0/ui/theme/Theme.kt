package com.example.learningkotlinprj0.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = white,
    secondary = white,
    tertiary = Pink80,
    background = black,  // Pitch black background
    surface = black,     // Black surface color
    onPrimary = black,   // Text on primary
    onSecondary = black, // Text on secondary
    onBackground = white, // White text on background
    onSurface = white    // White text on surfaces
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = white,  // White background for light mode
    surface = white,     // White surface
    onPrimary = black,   // Black text on primary
    onSecondary = black, // Black text on secondary
    onBackground = black, // Black text on background
    onSurface = black    // Black text on surfaces
)


@Composable
fun LearningKotlinPrj0Theme(
    darkTheme: Boolean = true, // Force dark mode
    dynamicColor: Boolean = false, // Disable dynamic colors for consistency
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
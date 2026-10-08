package com.example.exploredigimon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DigimonLightColorScheme = lightColorScheme(
    primary = DigiviceOrange,
    onPrimary = CardSurface,
    primaryContainer = DigiviceOrangeContainer,
    onPrimaryContainer = DigiviceOrangeDark,
    secondary = DigitalCyan,
    onSecondary = CardSurface,
    secondaryContainer = DigitalCyanContainer,
    onSecondaryContainer = DigitalCyanDark,
    tertiary = VirusPurple,
    onTertiary = CardSurface,
    tertiaryContainer = VirusPurpleContainer,
    onTertiaryContainer = VirusPurpleText,
    background = BackgroundLight,
    onBackground = TextTitle,
    surface = CardSurface,
    onSurface = TextTitle,
    surfaceVariant = DigiviceOrangeContainer,
    onSurfaceVariant = TextBody,
    outline = BorderSubtle,
    outlineVariant = BorderSubtle
)

@Composable
fun ExploreDigimonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DigimonLightColorScheme,
        typography = Typography,
        content = content
    )
}
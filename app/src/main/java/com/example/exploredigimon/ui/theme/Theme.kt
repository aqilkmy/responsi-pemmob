package com.example.exploredigimon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MinimalistColorScheme = lightColorScheme(
    primary = AccentPrimary,
    onPrimary = PureWhite,
    primaryContainer = AccentContainer,
    onPrimaryContainer = OnAccentContainer,
    secondary = TextSecondary,
    onSecondary = PureWhite,
    secondaryContainer = ChipBackground,
    onSecondaryContainer = ChipText,
    tertiary = AccentPrimary,
    onTertiary = PureWhite,
    background = OffWhite,
    onBackground = TextPrimary,
    surface = PureWhite,
    onSurface = TextPrimary,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = TextSecondary,
    outline = BorderLight,
    outlineVariant = BorderLight,
    error = StatusError,
    onError = PureWhite,
    errorContainer = StatusErrorContainer,
    onErrorContainer = StatusError
)

@Composable
fun ExploreDigimonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MinimalistColorScheme,
        typography = Typography,
        content = content
    )
}
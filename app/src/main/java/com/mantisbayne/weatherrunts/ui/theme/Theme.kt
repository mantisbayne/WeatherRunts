package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.mantisbayne.weatherrunts.ui.theme.AppTypography
import com.mantisbayne.weatherrunts.ui.theme.backgroundDark
import com.mantisbayne.weatherrunts.ui.theme.backgroundDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.backgroundDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.backgroundLight
import com.mantisbayne.weatherrunts.ui.theme.backgroundLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.backgroundLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.errorContainerDark
import com.mantisbayne.weatherrunts.ui.theme.errorContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.errorContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.errorContainerLight
import com.mantisbayne.weatherrunts.ui.theme.errorContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.errorContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.errorDark
import com.mantisbayne.weatherrunts.ui.theme.errorDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.errorDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.errorLight
import com.mantisbayne.weatherrunts.ui.theme.errorLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.errorLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceDark
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceLight
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseOnSurfaceLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryDark
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryLight
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inversePrimaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceDark
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceLight
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.inverseSurfaceLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundDark
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundLight
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onBackgroundLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerDark
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerLight
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorDark
import com.mantisbayne.weatherrunts.ui.theme.onErrorDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorLight
import com.mantisbayne.weatherrunts.ui.theme.onErrorLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onErrorLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryDark
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryLight
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onPrimaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryDark
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryLight
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSecondaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceDark
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceLight
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantDark
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantLight
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onSurfaceVariantLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryDark
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryLight
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.onTertiaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineDark
import com.mantisbayne.weatherrunts.ui.theme.outlineDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineLight
import com.mantisbayne.weatherrunts.ui.theme.outlineLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantDark
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantLight
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.outlineVariantLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryDark
import com.mantisbayne.weatherrunts.ui.theme.primaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryLight
import com.mantisbayne.weatherrunts.ui.theme.primaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.primaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.scrimDark
import com.mantisbayne.weatherrunts.ui.theme.scrimDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.scrimDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.scrimLight
import com.mantisbayne.weatherrunts.ui.theme.scrimLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.scrimLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryDark
import com.mantisbayne.weatherrunts.ui.theme.secondaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryLight
import com.mantisbayne.weatherrunts.ui.theme.secondaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.secondaryLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceBrightLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerHighestLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceContainerLowestLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceDimLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantDark
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantLight
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.surfaceVariantLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerDark
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerLight
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryContainerLightMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryDark
import com.mantisbayne.weatherrunts.ui.theme.tertiaryDarkHighContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryDarkMediumContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryLight
import com.mantisbayne.weatherrunts.ui.theme.tertiaryLightHighContrast
import com.mantisbayne.weatherrunts.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun WeatherRuntsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}


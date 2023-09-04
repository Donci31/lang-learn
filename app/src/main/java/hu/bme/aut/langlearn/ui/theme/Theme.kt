package hu.bme.aut.langlearn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPurple,
    secondary = SaddleBrown,
    background = MidNightGreen,
    onSurface = BananaMania,
    outline = Black
)

private val LightColorScheme = lightColorScheme(
    primary = SkyBlueCrayola,
    secondary = OrangeYellowCrayola,
    background = Cultured,
    onSurface = Whited,
    outline = Black
)

@Composable
fun LangLearnTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
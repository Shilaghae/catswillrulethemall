package theming

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cats.composeapp.generated.resources.LexendDeca_Bold
import cats.composeapp.generated.resources.LexendDeca_Medium
import cats.composeapp.generated.resources.LexendDeca_Regular
import cats.composeapp.generated.resources.LexendDeca_SemiBold
import cats.composeapp.generated.resources.LexendDeca_Thin
import cats.composeapp.generated.resources.LexendExa_Bold
import cats.composeapp.generated.resources.LexendExa_Medium
import cats.composeapp.generated.resources.LexendExa_Regular
import cats.composeapp.generated.resources.LexendExa_SemiBold
import cats.composeapp.generated.resources.LexendExa_Thin
import cats.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font


@Composable
private fun LexenDecaFontFamily() = FontFamily(
    Font(Res.font.LexendDeca_Thin, weight = FontWeight.Light),
    Font(Res.font.LexendDeca_Regular, weight = FontWeight.Normal),
    Font(Res.font.LexendDeca_Medium, weight = FontWeight.Medium),
    Font(Res.font.LexendDeca_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.LexendDeca_Bold, weight = FontWeight.Bold)
)

@Composable
private fun LexenExaFontFamily() = FontFamily(
    Font(Res.font.LexendExa_Thin, weight = FontWeight.Light),
    Font(Res.font.LexendExa_Regular, weight = FontWeight.Normal),
    Font(Res.font.LexendExa_Medium, weight = FontWeight.Medium),
    Font(Res.font.LexendExa_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.LexendExa_Bold, weight = FontWeight.Bold)
)

// Default Material 3 typography values
val baseline = Typography()

@Composable
fun LexenDecaTypography() = Typography().run {
    val decaFontFamily = LexenDecaFontFamily()
    val exaFontFamily = LexenExaFontFamily()
    copy(
        displayLarge = baseline.displayLarge.copy(fontFamily = decaFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = decaFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = decaFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = decaFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = decaFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = decaFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = decaFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = decaFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = decaFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = exaFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = exaFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = exaFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = exaFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = exaFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = exaFontFamily),
    )
}


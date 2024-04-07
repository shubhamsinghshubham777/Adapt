/*
 * Copyright 2023 Shubham Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package design.adapt.cupertino

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import design.adapt.LocalContentColor
import design.adapt.LocalTextStyle

@Composable
fun MacOSButton(
    onClick: () -> Unit,
    style: MacOSButtonStyle = MacOSButtonDefaults.style,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    colors: MacOSButtonColors = MacOSButtonDefaults.colors(style),
    enabled: Boolean = true,
    shape: Shape = MacOSButtonDefaults.shape,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colorScheme = MacOSTheme.colorScheme
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColor = remember(colors, enabled) {
        if (enabled) colors.containerColor else colors.disabledContainerColor
    }

    val containerOverlayModifier = remember(colors, enabled, isPressed, shape) {
        if (
            enabled &&
            !isPressed &&
            colors.containerOverlayGradientColors != null
        ) {
            Modifier.background(
                brush = Brush.verticalGradient(colors = colors.containerOverlayGradientColors),
                shape = shape
            )
        } else Modifier
    }

    val pressOverlayModifier = remember(enabled, isPressed, shape, isSystemInDarkTheme) {
        val overlayColor = when {
            enabled && isPressed && isSystemInDarkTheme -> colorScheme.systemWhite.copy(alpha = 0.1f)
            enabled && isPressed && !isSystemInDarkTheme -> colorScheme.systemBlack.copy(alpha = 0.1f)
            else -> Color.Transparent
        }
        Modifier.background(color = overlayColor, shape = shape)
    }

    val contentColor = remember(colors, enabled) {
        if (enabled) colors.contentColor else colors.disabledContentColor
    }

    val shadowColor = remember(colors, enabled) {
        if (enabled) colors.shadowColor else colors.disabledShadowColor
    }

    val borderColor = remember(colors, enabled) {
        if (enabled) colors.borderColor else colors.disabledBorderColor
    }

    Box(
        modifier = modifier
            .shadow(
                elevation = 2.5.dp,
                shape = shape,
                ambientColor = shadowColor,
                spotColor = shadowColor,
            )
            .background(color = containerColor, shape = shape)
            .then(containerOverlayModifier)
            .then(pressOverlayModifier)
            .border(color = borderColor, shape = shape, width = 0.5.dp)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 7.dp, vertical = 3.dp),
        content = {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                LocalTextStyle provides MacOSTheme.typography.bodyRegular,
                content = { text() }
            )
        }
    )
}

@Immutable
data class MacOSButtonColors(
    val containerColor: Color,
    val containerOverlayGradientColors: List<Color>?,
    val contentColor: Color,
    val borderColor: Color,
    val shadowColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledBorderColor: Color,
    val disabledShadowColor: Color,
)

@Immutable
enum class MacOSButtonStyle { Primary, Secondary, Destructive }

object MacOSButtonDefaults {
    val shape = RoundedCornerShape(5.dp)
    val style = MacOSButtonStyle.Primary

    @Composable
    fun colors(style: MacOSButtonStyle) = when (style) {
        MacOSButtonStyle.Primary -> primaryButtonColors()
        MacOSButtonStyle.Secondary -> secondaryButtonColors()
        MacOSButtonStyle.Destructive -> destructiveButtonColors()
    }

    @Composable
    fun primaryButtonColors(
        containerColor: Color = MacOSTheme.colorScheme.systemBlue,
        containerOverlayGradientColors: List<Color> = listOf(
            MacOSTheme.colorScheme.systemWhite.copy(alpha = 0.17f),
            Color.Transparent,
        ),
        contentColor: Color = MacOSTheme.colorScheme.systemWhite,
        borderColor: Color = MacOSTheme.colorScheme.systemBlue.copy(alpha = 0.12f),
        shadowColor: Color = MacOSTheme.colorScheme.systemBlue.copy(alpha = 0.24f),
        disabledContainerColor: Color = MacOSTheme.colorScheme.systemWhite.copy(alpha = 0.5f),
        disabledContentColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.25f),
        disabledBorderColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.03f),
        disabledShadowColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.15f),
    ) = MacOSButtonColors(
        containerColor = containerColor,
        containerOverlayGradientColors = containerOverlayGradientColors,
        contentColor = contentColor,
        borderColor = borderColor,
        shadowColor = shadowColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledBorderColor = disabledBorderColor,
        disabledShadowColor = disabledShadowColor,
    )

    @Composable
    fun secondaryButtonColors(
        containerColor: Color = MacOSTheme.colorScheme.systemWhite,
        contentColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.85f),
        borderColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.05f),
        shadowColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.3f),
        disabledContainerColor: Color = MacOSTheme.colorScheme.systemWhite.copy(alpha = 0.5f),
        disabledContentColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.25f),
        disabledBorderColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.03f),
        disabledShadowColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.15f),
    ) = MacOSButtonColors(
        containerColor = containerColor,
        containerOverlayGradientColors = null,
        contentColor = contentColor,
        borderColor = borderColor,
        shadowColor = shadowColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledBorderColor = disabledBorderColor,
        disabledShadowColor = disabledShadowColor,
    )

    @Composable
    fun destructiveButtonColors(
        containerColor: Color = MacOSTheme.colorScheme.systemWhite,
        contentColor: Color = MacOSTheme.colorScheme.systemRed,
        borderColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.05f),
        shadowColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.3f),
    ) = MacOSButtonColors(
        containerColor = containerColor,
        containerOverlayGradientColors = null,
        contentColor = contentColor,
        borderColor = borderColor,
        shadowColor = shadowColor,
        disabledContainerColor = containerColor,
        disabledContentColor = contentColor,
        disabledBorderColor = borderColor,
        disabledShadowColor = shadowColor,
    )
}

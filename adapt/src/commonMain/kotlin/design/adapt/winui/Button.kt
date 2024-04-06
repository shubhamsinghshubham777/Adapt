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

package design.adapt.winui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import design.adapt.LocalContentColor

@Composable
fun WindowsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: WindowsButtonStyle = WindowsButtonStyle.Accent,
    size: WindowsButtonSize = WindowsButtonSize.Standard,
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    iconSide: WindowsButtonIconSide = WindowsButtonIconSide.Start,
    contentPadding: PaddingValues = WindowsButtonDefaults.contentPadding(
        hasIcon = icon != null,
        hasText = text != null,
        size = size,
        style = style,
    ),
    colors: WindowsButtonColors = when (style) {
        WindowsButtonStyle.Standard -> WindowsButtonDefaults.standardButtonColors()
        WindowsButtonStyle.Accent -> WindowsButtonDefaults.accentButtonColors()
        WindowsButtonStyle.Subtle -> WindowsButtonDefaults.subtleButtonColors()
    },
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(4.dp),
    focusBorderShape: Shape = RoundedCornerShape(7.dp),
) {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val isCompactButton = remember(size) { size == WindowsButtonSize.Compact }
    val isIconOnlyButton = remember(icon, text) { icon != null && text == null }

    LaunchedEffect(isPressed) {
        println("isPressed: $isPressed")
    }

    val buttonMargin = remember(isCompactButton, isIconOnlyButton) {
        if (isCompactButton) {
            PaddingValues(
                horizontal = if (isIconOnlyButton) 9.dp else 1.dp,
                vertical = 9.dp
            )
        } else PaddingValues()
    }

    val containerColor = remember(colors, enabled, isHovered, isPressed) {
        when {
            enabled && isHovered -> colors.hoveredContainerColor
            enabled && isPressed -> colors.pressedContainerColor
            enabled -> colors.containerColor
            else -> colors.disabledContainerColor
        }
    }

    val contentColor = remember(colors, enabled, isHovered, isPressed) {
        when {
            enabled && isHovered -> colors.hoveredContentColor
            enabled && isPressed -> colors.pressedContentColor
            enabled -> colors.contentColor
            else -> colors.disabledContentColor
        }
    }

    val processedIcon = remember(icon, isIconOnlyButton) {
        icon?.let { safeIcon ->
            movableContentOf {
                Box(
                    modifier = if (isIconOnlyButton) Modifier
                    else Modifier.padding(top = 3.dp, bottom = 2.dp),
                ) {
                    safeIcon()
                }
            }
        }
    }

    val borderColors: List<Color>? = remember(colors, enabled, isPressed) {
        when {
            !enabled -> colors.disabledBorderColor?.let(::listOf)
            enabled && isPressed -> colors.pressedBorderColor?.let(::listOf)
            else -> colors.borderColors
        }
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Row(
            modifier = modifier
                .padding(buttonMargin)
                .focusBorder(interactionSource = interactionSource, shape = focusBorderShape)
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                )
                .background(color = containerColor, shape = shape)
                .then(
                    when (borderColors?.size) {
                        1 -> Modifier.border(
                            width = 1.dp,
                            color = borderColors.first(),
                            shape = shape
                        )

                        2 -> Modifier.border(
                            width = 1.dp,
                            brush = Brush.verticalGradient(borderColors),
                            shape = shape
                        )

                        else -> Modifier
                    }
                )
                .padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconSide == WindowsButtonIconSide.Start) processedIcon?.invoke()
            text?.invoke()
            if (iconSide == WindowsButtonIconSide.End) processedIcon?.invoke()
        }
    }
}

@Immutable
enum class WindowsButtonIconSide { Start, End }

@Immutable
data class WindowsButtonColors(
    val containerColor: Color,
    val hoveredContainerColor: Color,
    val pressedContainerColor: Color,
    val disabledContainerColor: Color,
    val contentColor: Color,
    val hoveredContentColor: Color,
    val pressedContentColor: Color,
    val disabledContentColor: Color,
    val borderColors: List<Color>?,
    val pressedBorderColor: Color?,
    val disabledBorderColor: Color?,
)

object WindowsButtonDefaults {
    @Composable
    fun standardButtonColors(
        containerColor: Color = LocalWindowsColorScheme.current.fillControlDefault,
        hoveredContainerColor: Color = LocalWindowsColorScheme.current.fillControlSecondary,
        pressedContainerColor: Color = LocalWindowsColorScheme.current.fillControlTertiary,
        disabledContainerColor: Color = LocalWindowsColorScheme.current.fillControlDisabled,
        contentColor: Color = LocalWindowsColorScheme.current.fillTextPrimary,
        hoveredContentColor: Color = LocalWindowsColorScheme.current.fillTextPrimary,
        pressedContentColor: Color = LocalWindowsColorScheme.current.fillTextSecondary,
        disabledContentColor: Color = LocalWindowsColorScheme.current.fillTextDisabled,
        borderColors: List<Color> = LocalWindowsColorScheme.current.elevationControlBorder,
        pressedBorderColor: Color = LocalWindowsColorScheme.current.strokeControlDefault,
        disabledBorderColor: Color = LocalWindowsColorScheme.current.strokeControlDefault,
    ) = WindowsButtonColors(
        containerColor = containerColor,
        hoveredContainerColor = hoveredContainerColor,
        pressedContainerColor = pressedContainerColor,
        disabledContainerColor = disabledContainerColor,
        contentColor = contentColor,
        hoveredContentColor = hoveredContentColor,
        pressedContentColor = pressedContentColor,
        disabledContentColor = disabledContentColor,
        borderColors = borderColors,
        pressedBorderColor = pressedBorderColor,
        disabledBorderColor = disabledBorderColor,
    )

    @Composable
    fun accentButtonColors(
        containerColor: Color = LocalWindowsColorScheme.current.fillAccentDefault,
        hoveredContainerColor: Color = LocalWindowsColorScheme.current.fillAccentSecondary,
        pressedContainerColor: Color = LocalWindowsColorScheme.current.fillAccentTertiary,
        disabledContainerColor: Color = LocalWindowsColorScheme.current.fillAccentDisabled,
        contentColor: Color = LocalWindowsColorScheme.current.fillTextOnAccentPrimary,
        hoveredContentColor: Color = LocalWindowsColorScheme.current.fillTextOnAccentPrimary,
        pressedContentColor: Color = LocalWindowsColorScheme.current.fillTextOnAccentSecondary,
        disabledContentColor: Color = LocalWindowsColorScheme.current.fillTextOnAccentDisabled,
        borderColors: List<Color> = LocalWindowsColorScheme.current.elevationAccentControlBorder,
        pressedBorderColor: Color = LocalWindowsColorScheme.current.strokeControlDefault,
        disabledBorderColor: Color = LocalWindowsColorScheme.current.strokeControlDefault,
    ) = WindowsButtonColors(
        containerColor = containerColor,
        hoveredContainerColor = hoveredContainerColor,
        pressedContainerColor = pressedContainerColor,
        disabledContainerColor = disabledContainerColor,
        contentColor = contentColor,
        hoveredContentColor = hoveredContentColor,
        pressedContentColor = pressedContentColor,
        disabledContentColor = disabledContentColor,
        borderColors = borderColors,
        pressedBorderColor = pressedBorderColor,
        disabledBorderColor = disabledBorderColor,
    )

    @Composable
    fun subtleButtonColors(
        containerColor: Color = LocalWindowsColorScheme.current.fillSubtleTransparent,
        hoveredContainerColor: Color = LocalWindowsColorScheme.current.fillSubtleSecondary,
        pressedContainerColor: Color = LocalWindowsColorScheme.current.fillSubtleTertiary,
        disabledContainerColor: Color = LocalWindowsColorScheme.current.fillSubtleDisabled,
        contentColor: Color = LocalWindowsColorScheme.current.fillTextPrimary,
        hoveredContentColor: Color = LocalWindowsColorScheme.current.fillTextPrimary,
        pressedContentColor: Color = LocalWindowsColorScheme.current.fillTextSecondary,
        disabledContentColor: Color = LocalWindowsColorScheme.current.fillTextDisabled,
    ) = WindowsButtonColors(
        containerColor = containerColor,
        hoveredContainerColor = hoveredContainerColor,
        pressedContainerColor = pressedContainerColor,
        disabledContainerColor = disabledContainerColor,
        contentColor = contentColor,
        hoveredContentColor = hoveredContentColor,
        pressedContentColor = pressedContentColor,
        disabledContentColor = disabledContentColor,
        borderColors = null,
        pressedBorderColor = null,
        disabledBorderColor = null,
    )

    fun contentPadding(
        hasIcon: Boolean,
        hasText: Boolean,
        size: WindowsButtonSize,
        style: WindowsButtonStyle,
    ): PaddingValues {
        val iconOnly = hasIcon && !hasText
        val textOnly = !hasIcon && hasText
        val hasAllContent = hasIcon && hasText
        val compact = size == WindowsButtonSize.Compact
        val accentStyle = style == WindowsButtonStyle.Accent

        return when {
            !compact && accentStyle && textOnly -> PaddingValues(
                start = 47.5.dp,
                top = 5.dp,
                end = 47.5.dp,
                bottom = 7.dp
            )

            !compact && !accentStyle && textOnly -> PaddingValues(
                start = 46.5.dp,
                top = 4.dp,
                end = 46.5.dp,
                bottom = 6.dp
            )

            !compact && hasAllContent -> PaddingValues(
                start = 34.5.dp,
                top = 4.dp,
                end = 34.5.dp,
                bottom = 6.dp,
            )

            !compact && iconOnly -> PaddingValues(7.dp)

            compact && textOnly -> PaddingValues(start = 11.dp, end = 11.dp, bottom = 2.dp)

            compact && iconOnly -> PaddingValues(3.dp)

            compact && hasAllContent -> PaddingValues(start = 11.dp, end = 11.dp, bottom = 2.dp)

            else -> PaddingValues()
        }
    }
}

enum class WindowsButtonStyle { Standard, Accent, Subtle }

enum class WindowsButtonSize { Standard, Compact }

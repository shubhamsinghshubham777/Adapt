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

package design.adapt.ios

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import design.adapt.LocalContentColor
import design.adapt.LocalTextStyle

@Composable
fun IOSButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: IOSButtonSize = IOSButtonSize.Small,
    style: IOSButtonStyle = IOSButtonStyle.Borderless,
    colors: IOSButtonColors = IOSButtonDefaults.buttonColors(),
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    shape: Shape = IOSButtonDefaults.shape(
        isIconOnly = icon != null && text == null,
        size = size
    ),
    enabled: Boolean = true,
    onMaterial: Boolean = false,
    textStyle: TextStyle = IOSButtonDefaults.textStyle(size),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current

    val isInLightMode = !isSystemInDarkTheme()
    val isIconOnly = remember(icon, text) { icon != null && text == null }

    val isPressed by interactionSource.collectIsPressedAsState()
    val onPressAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 0f,
        animationSpec = when {
            style == IOSButtonStyle.Borderless && isPressed -> tween(durationMillis = 0)
            else -> spring()
        },
    )

    val horizontalArrangement: Dp = remember(size) {
        when (size) {
            IOSButtonSize.Small -> 3.dp
            IOSButtonSize.Medium -> 4.dp
            IOSButtonSize.Large -> 4.dp
        }
    }

    val horizontalPadding: Dp = remember(size) {
        when (size) {
            IOSButtonSize.Small -> 10.dp
            IOSButtonSize.Medium -> 14.dp
            IOSButtonSize.Large -> 20.dp
        }
    }

    val verticalPadding: Dp = remember(size) {
        when (size) {
            IOSButtonSize.Small -> 4.dp
            IOSButtonSize.Medium -> 7.dp
            IOSButtonSize.Large -> 14.dp
        }
    }

    val containerColor: Color = remember(style, enabled, isInLightMode, onMaterial, colors) {
        when (style) {
            IOSButtonStyle.Borderless -> {
                if (enabled) colors.borderlessContainerColor
                else colors.disabledBorderlessContainerColor
            }

            IOSButtonStyle.BezeledGray -> {
                if (enabled) colors.bezeledGrayContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledGrayContainerColor
                else colors.disabledBezeledGrayContainerColor
            }

            IOSButtonStyle.Bezeled -> {
                if (enabled) colors.bezeledContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledContainerColor
                else colors.disabledBezeledContainerColor
            }

            IOSButtonStyle.Filled -> {
                if (enabled) colors.filledContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialFilledContainerColor
                else colors.disabledFilledContainerColor
            }
        }
    }

    val contentColor: Color = remember(style, enabled, isInLightMode, onMaterial, colors) {
        when (style) {
            IOSButtonStyle.Borderless -> {
                if (enabled) colors.borderlessContentColor
                else colors.disabledBorderlessContentColor
            }

            IOSButtonStyle.BezeledGray -> {
                if (enabled) colors.bezeledGrayContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledGrayContentColor
                else colors.disabledBezeledGrayContentColor
            }

            IOSButtonStyle.Bezeled -> {
                if (enabled) colors.bezeledContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledContentColor
                else colors.disabledBezeledContentColor
            }

            IOSButtonStyle.Filled -> {
                if (enabled) colors.filledContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialFilledContentColor
                else colors.disabledFilledContentColor
            }
        }
    }

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .graphicsLayer {
                alpha = if (style == IOSButtonStyle.Borderless) 1 - onPressAlpha else 1f
            }
            .background(color = containerColor, shape = shape)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawOutline(
                        outline = shape.createOutline(this.size, layoutDirection, density),
                        color = Color.Black.copy(alpha = 0.5f),
                        alpha = if (style != IOSButtonStyle.Borderless) onPressAlpha else 0f,
                    )
                }
            }
            .padding(
                horizontal = if (isIconOnly) verticalPadding else horizontalPadding,
                vertical = verticalPadding,
            ),
        horizontalArrangement = Arrangement.spacedBy(horizontalArrangement),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let { safeIcon ->
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                Box(
                    modifier = Modifier.defaultMinSize(
                        minWidth = IOSButtonDefaults.IconSize,
                        minHeight = IOSButtonDefaults.IconSize,
                    ),
                    contentAlignment = Alignment.Center,
                ) {
                    safeIcon()
                }
            }
        }

        text?.let { safeText ->
            CompositionLocalProvider(
                LocalTextStyle provides textStyle,
                LocalContentColor provides contentColor,
                content = safeText,
            )
        }
    }
}

@Immutable
data class IOSButtonColors(
    val borderlessContainerColor: Color,
    val borderlessContentColor: Color,
    val bezeledGrayContainerColor: Color,
    val bezeledGrayContentColor: Color,
    val bezeledContainerColor: Color,
    val bezeledContentColor: Color,
    val filledContainerColor: Color,
    val filledContentColor: Color,
    val disabledBorderlessContainerColor: Color,
    val disabledBorderlessContentColor: Color,
    val disabledBezeledGrayContainerColor: Color,
    val disabledBezeledGrayContentColor: Color,
    val disabledBezeledContainerColor: Color,
    val disabledBezeledContentColor: Color,
    val disabledFilledContainerColor: Color,
    val disabledFilledContentColor: Color,
    val disabledOnMaterialBezeledGrayContainerColor: Color,
    val disabledOnMaterialBezeledGrayContentColor: Color,
    val disabledOnMaterialBezeledContainerColor: Color,
    val disabledOnMaterialBezeledContentColor: Color,
    val disabledOnMaterialFilledContainerColor: Color,
    val disabledOnMaterialFilledContentColor: Color,
)

object IOSButtonDefaults {
    @Composable
    fun buttonColors(
        borderlessContainerColor: Color = Color.Transparent,
        borderlessContentColor: Color = IOSTheme.colorScheme.systemBlue,
        bezeledGrayContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        bezeledGrayContentColor: Color = IOSTheme.colorScheme.systemBlue,
        bezeledContainerColor: Color = IOSTheme.colorScheme.systemBlue.copy(alpha = 0.15f),
        bezeledContentColor: Color = IOSTheme.colorScheme.systemBlue,
        filledContainerColor: Color = IOSTheme.colorScheme.systemBlue,
        filledContentColor: Color = IOSTheme.colorScheme.systemWhite,
        disabledBorderlessContainerColor: Color = Color.Transparent,
        disabledBorderlessContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledBezeledGrayContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledBezeledGrayContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledBezeledContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledBezeledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledFilledContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledFilledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialBezeledGrayContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialBezeledGrayContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialBezeledContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialBezeledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialFilledContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialFilledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
    ): IOSButtonColors = IOSButtonColors(
        borderlessContainerColor = borderlessContainerColor,
        borderlessContentColor = borderlessContentColor,
        bezeledGrayContainerColor = bezeledGrayContainerColor,
        bezeledGrayContentColor = bezeledGrayContentColor,
        bezeledContainerColor = bezeledContainerColor,
        bezeledContentColor = bezeledContentColor,
        filledContainerColor = filledContainerColor,
        filledContentColor = filledContentColor,
        disabledBorderlessContainerColor = disabledBorderlessContainerColor,
        disabledBorderlessContentColor = disabledBorderlessContentColor,
        disabledBezeledGrayContainerColor = disabledBezeledGrayContainerColor,
        disabledBezeledGrayContentColor = disabledBezeledGrayContentColor,
        disabledBezeledContainerColor = disabledBezeledContainerColor,
        disabledBezeledContentColor = disabledBezeledContentColor,
        disabledFilledContainerColor = disabledFilledContainerColor,
        disabledFilledContentColor = disabledFilledContentColor,
        disabledOnMaterialBezeledGrayContainerColor = disabledOnMaterialBezeledGrayContainerColor,
        disabledOnMaterialBezeledGrayContentColor = disabledOnMaterialBezeledGrayContentColor,
        disabledOnMaterialBezeledContainerColor = disabledOnMaterialBezeledContainerColor,
        disabledOnMaterialBezeledContentColor = disabledOnMaterialBezeledContentColor,
        disabledOnMaterialFilledContainerColor = disabledOnMaterialFilledContainerColor,
        disabledOnMaterialFilledContentColor = disabledOnMaterialFilledContentColor,
    )

    fun shape(isIconOnly: Boolean, size: IOSButtonSize): Shape = RoundedCornerShape(
        when {
            isIconOnly -> 400.dp
            size == IOSButtonSize.Large -> 12.dp
            else -> 40.dp
        }
    )

    @Composable
    fun textStyle(size: IOSButtonSize): TextStyle = with(LocalIOSTypography.current) {
        when (size) {
            IOSButtonSize.Small -> subheadlineRegular
            IOSButtonSize.Medium -> subheadlineRegular
            IOSButtonSize.Large -> bodyRegular
        }
    }

    val IconSize = 20.dp
}

enum class IOSButtonSize { Small, Medium, Large }
enum class IOSButtonStyle { Borderless, BezeledGray, Bezeled, Filled }

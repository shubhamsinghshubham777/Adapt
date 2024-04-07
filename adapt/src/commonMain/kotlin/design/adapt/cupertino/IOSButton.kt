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
    size: IOSButtonSize = IOSButtonDefaults.size,
    style: IOSButtonStyle = IOSButtonDefaults.style,
    colors: IOSButtonColors = IOSButtonDefaults.colors(style),
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    shape: Shape = IOSButtonDefaults.shape(
        isIconOnly = icon != null && text == null,
        size = size
    ),
    enabled: Boolean = true,
    onMaterial: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current

    val isInLightMode = !isSystemInDarkTheme()
    val isIconOnly = remember(icon, text) { icon != null && text == null }

    val isPressed by interactionSource.collectIsPressedAsState()
    val onPressAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 0f,
        animationSpec = when {
            style == IOSButtonStyle.Plain && isPressed -> tween(durationMillis = 0)
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

    val containerColor: Color = remember(enabled, isInLightMode, onMaterial, colors) {
        if (enabled) colors.containerColor
        // The material color should only be used with light mode
        else if (isInLightMode && onMaterial) colors.disabledOnMaterialTintedContainerColor
        else colors.disabledContainerColor
    }

    val contentColor: Color = remember(enabled, isInLightMode, onMaterial, colors) {
        if (enabled) colors.contentColor
        // The material color should only be used with light mode
        else if (isInLightMode && onMaterial) colors.disabledOnMaterialTintedContentColor
        else colors.disabledContentColor
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
                alpha = if (style == IOSButtonStyle.Plain) 1 - onPressAlpha else 1f
            }
            .background(color = containerColor, shape = shape)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawOutline(
                        outline = shape.createOutline(this.size, layoutDirection, density),
                        color = Color.Black.copy(alpha = 0.5f),
                        alpha = if (style != IOSButtonStyle.Plain) onPressAlpha else 0f,
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
                LocalContentColor provides contentColor,
                LocalTextStyle provides IOSButtonDefaults.textStyle(size),
                content = safeText,
            )
        }
    }
}

@Immutable
data class IOSButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledOnMaterialTintedContainerColor: Color,
    val disabledOnMaterialTintedContentColor: Color,
)

object IOSButtonDefaults {
    val size = IOSButtonSize.Small
    val style = IOSButtonStyle.Filled

    @Composable
    fun colors(style: IOSButtonStyle) = when (style) {
        IOSButtonStyle.Plain -> plainButtonColors()
        IOSButtonStyle.Gray -> grayButtonColors()
        IOSButtonStyle.Tinted -> tintedButtonColors()
        IOSButtonStyle.Filled -> filledButtonColors()
    }

    @Composable
    fun plainButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = IOSTheme.colorScheme.systemBlue,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialTintedContainerColor: Color = Color.Transparent,
        disabledOnMaterialTintedContentColor: Color = Color.Transparent,
    ) = IOSButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOnMaterialTintedContainerColor = disabledOnMaterialTintedContainerColor,
        disabledOnMaterialTintedContentColor = disabledOnMaterialTintedContentColor,
    )

    @Composable
    fun grayButtonColors(
        containerColor: Color = IOSTheme.colorScheme.fillTertiary,
        contentColor: Color = IOSTheme.colorScheme.systemBlue,
        disabledContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialTintedContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialTintedContentColor: Color = IOSTheme.colorScheme.labelTertiary,
    ) = IOSButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOnMaterialTintedContainerColor = disabledOnMaterialTintedContainerColor,
        disabledOnMaterialTintedContentColor = disabledOnMaterialTintedContentColor,
    )

    @Composable
    fun tintedButtonColors(
        containerColor: Color = IOSTheme.colorScheme.systemBlue.copy(alpha = 0.15f),
        contentColor: Color = IOSTheme.colorScheme.systemBlue,
        disabledContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialTintedContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialTintedContentColor: Color = IOSTheme.colorScheme.labelTertiary,
    ) = IOSButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOnMaterialTintedContainerColor = disabledOnMaterialTintedContainerColor,
        disabledOnMaterialTintedContentColor = disabledOnMaterialTintedContentColor,
    )

    @Composable
    fun filledButtonColors(
        containerColor: Color = IOSTheme.colorScheme.systemBlue,
        contentColor: Color = IOSTheme.colorScheme.systemWhite,
        disabledContainerColor: Color = IOSTheme.colorScheme.fillTertiary,
        disabledContentColor: Color = IOSTheme.colorScheme.labelTertiary,
        disabledOnMaterialTintedContainerColor: Color = IOSTheme.colorScheme.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialTintedContentColor: Color = IOSTheme.colorScheme.labelTertiary,
    ) = IOSButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledOnMaterialTintedContainerColor = disabledOnMaterialTintedContainerColor,
        disabledOnMaterialTintedContentColor = disabledOnMaterialTintedContentColor,
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
enum class IOSButtonStyle { Plain, Gray, Tinted, Filled }

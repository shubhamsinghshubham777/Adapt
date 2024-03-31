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
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CupertinoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: CupertinoButtonSize = CupertinoButtonSize.Small,
    style: CupertinoButtonStyle = CupertinoButtonStyle.Borderless,
    colors: CupertinoButtonColors = CupertinoButtonDefaults.buttonColors(),
    shape: Shape? = null,
    enabled: Boolean = true,
    onMaterial: Boolean = false,
    textStyle: TextStyle = CupertinoButtonDefaults.textStyle(size),
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
) {
    val isInLightMode = !isSystemInDarkTheme()

    val isIconOnly = remember(icon, text) { icon != null && text == null }

    val defaultShape = remember(shape, isIconOnly, size) {
        shape ?: CupertinoButtonDefaults.shape(isIconOnly = isIconOnly, size = size)
    }

    val horizontalArrangement: Dp = remember(size) {
        when (size) {
            CupertinoButtonSize.Small -> 3.dp
            CupertinoButtonSize.Medium -> 4.dp
            CupertinoButtonSize.Large -> 4.dp
        }
    }

    val horizontalPadding: Dp = remember(size) {
        when (size) {
            CupertinoButtonSize.Small -> 10.dp
            CupertinoButtonSize.Medium -> 14.dp
            CupertinoButtonSize.Large -> 20.dp
        }
    }

    val verticalPadding: Dp = remember(size) {
        when (size) {
            CupertinoButtonSize.Small -> 4.dp
            CupertinoButtonSize.Medium -> 7.dp
            CupertinoButtonSize.Large -> 14.dp
        }
    }

    val containerColor: Color = remember(style, enabled, isInLightMode, onMaterial, colors) {
        when (style) {
            CupertinoButtonStyle.Borderless -> {
                if (enabled) colors.borderlessContainerColor
                else colors.disabledBorderlessContainerColor
            }

            CupertinoButtonStyle.BezeledGray -> {
                if (enabled) colors.bezeledGrayContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledGrayContainerColor
                else colors.disabledBezeledGrayContainerColor
            }

            CupertinoButtonStyle.Bezeled -> {
                if (enabled) colors.bezeledContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledContainerColor
                else colors.disabledBezeledContainerColor
            }

            CupertinoButtonStyle.Filled -> {
                if (enabled) colors.filledContainerColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialFilledContainerColor
                else colors.disabledFilledContainerColor
            }
        }
    }

    val contentColor: Color = remember(style, enabled, isInLightMode, onMaterial, colors) {
        when (style) {
            CupertinoButtonStyle.Borderless -> {
                if (enabled) colors.borderlessContentColor
                else colors.disabledBorderlessContentColor
            }

            CupertinoButtonStyle.BezeledGray -> {
                if (enabled) colors.bezeledGrayContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledGrayContentColor
                else colors.disabledBezeledGrayContentColor
            }

            CupertinoButtonStyle.Bezeled -> {
                if (enabled) colors.bezeledContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialBezeledContentColor
                else colors.disabledBezeledContentColor
            }

            CupertinoButtonStyle.Filled -> {
                if (enabled) colors.filledContentColor
                // The material color should only be used with light mode
                else if (isInLightMode && onMaterial) colors.disabledOnMaterialFilledContentColor
                else colors.disabledFilledContentColor
            }
        }
    }

    Row(
        modifier = modifier
            .pointerInput(Unit) { detectTapGestures { onClick() } }
            .background(color = containerColor, shape = defaultShape)
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
                        minWidth = CupertinoButtonDefaults.IconSize,
                        minHeight = CupertinoButtonDefaults.IconSize,
                    ),
                    contentAlignment = Alignment.Center,
                ) {
                    safeIcon()
                }
            }
        }

        text?.let { safeText ->
            CompositionLocalProvider(
                LocalCupertinoTextStyle provides textStyle,
                LocalContentColor provides contentColor,
                content = safeText,
            )
        }
    }
}

@Immutable
data class CupertinoButtonColors(
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

object CupertinoButtonDefaults {
    @Composable
    fun buttonColors(
        borderlessContainerColor: Color = Color.Transparent,
        borderlessContentColor: Color = CupertinoTheme.colors.systemBlue,
        bezeledGrayContainerColor: Color = CupertinoTheme.colors.fillTertiary,
        bezeledGrayContentColor: Color = CupertinoTheme.colors.systemBlue,
        bezeledContainerColor: Color = CupertinoTheme.colors.systemBlue.copy(alpha = 0.15f),
        bezeledContentColor: Color = CupertinoTheme.colors.systemBlue,
        filledContainerColor: Color = CupertinoTheme.colors.systemBlue,
        filledContentColor: Color = CupertinoTheme.colors.systemWhite,
        disabledBorderlessContainerColor: Color = Color.Transparent,
        disabledBorderlessContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledBezeledGrayContainerColor: Color = CupertinoTheme.colors.fillTertiary,
        disabledBezeledGrayContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledBezeledContainerColor: Color = CupertinoTheme.colors.fillTertiary,
        disabledBezeledContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledFilledContainerColor: Color = CupertinoTheme.colors.fillTertiary,
        disabledFilledContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledOnMaterialBezeledGrayContainerColor: Color = CupertinoTheme.colors.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialBezeledGrayContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledOnMaterialBezeledContainerColor: Color = CupertinoTheme.colors.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialBezeledContentColor: Color = CupertinoTheme.colors.labelTertiary,
        disabledOnMaterialFilledContainerColor: Color = CupertinoTheme.colors.systemWhite.copy(alpha = 0.12f),
        disabledOnMaterialFilledContentColor: Color = CupertinoTheme.colors.labelTertiary,
    ): CupertinoButtonColors = CupertinoButtonColors(
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

    fun shape(isIconOnly: Boolean, size: CupertinoButtonSize): Shape = RoundedCornerShape(
        when {
            isIconOnly -> 400.dp
            size == CupertinoButtonSize.Large -> 12.dp
            else -> 40.dp
        }
    )

    @Composable
    fun textStyle(size: CupertinoButtonSize): TextStyle = with(LocalCupertinoTypography.current) {
        when (size) {
            CupertinoButtonSize.Small -> subheadlineRegular
            CupertinoButtonSize.Medium -> subheadlineRegular
            CupertinoButtonSize.Large -> bodyRegular
        }
    }

    val IconSize = 20.dp
}

enum class CupertinoButtonSize { Small, Medium, Large }
enum class CupertinoButtonStyle { Borderless, BezeledGray, Bezeled, Filled }

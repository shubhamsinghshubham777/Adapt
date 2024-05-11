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

package design.adapt.windows

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import design.adapt.LocalTextStyle
import design.adapt.animateHorizontalAlignmentAsState

/**
 * @param alignTextToStart Aligns the [text] composable before the toggle switch. Defaults to
 * false, meaning the [text] composable will be aligned after the toggle switch.
 */
@Composable
fun WindowsToggleSwitch(
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    header: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    alignTextToStart: Boolean = false,
    enabled: Boolean = true,
    trackShape: Shape = CircleShape,
    thumbShape: Shape = CircleShape,
    colors: WindowsToggleSwitchColors = WindowsToggleSwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    var currentDragOffsetX by remember { mutableStateOf<Float?>(null) }
    val isDragged by remember {
        derivedStateOf { currentDragOffsetX != null }
    }

    val paddedText = remember(text, alignTextToStart) {
        movableContentOf {
            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(
                        start = if (alignTextToStart) 10.dp else 12.dp,
                        end = if (alignTextToStart) 12.dp else 10.dp,
                    )
            ) {
                CompositionLocalProvider(
                    LocalTextStyle provides WindowsTheme.typography.body.copy(
                        color = WindowsTheme.colorScheme.fillTextPrimary,
                        textAlign = if (alignTextToStart) TextAlign.End else TextAlign.Start,
                    ),
                    content = { text?.invoke() },
                )
            }
        }
    }

    /**
     * Progress value from 0f to 1f depicting how much the user has dragged the toggle switch
     */
    val thumbDragProgress by remember(density) {
        derivedStateOf {
            currentDragOffsetX?.let { safeOffsetX ->
                val width = with(density) {
                    WindowsToggleSwitchDefaults.ToggleSize.width.toPx()
                }
                return@derivedStateOf (safeOffsetX / width).coerceIn(0f, 1f)
            }
            return@derivedStateOf 0f
        }
    }

    val thumbAlignment by animateHorizontalAlignmentAsState(
        targetBiasValue = if (isDragged) lerp(start = -1f, stop = 1f, fraction = thumbDragProgress)
        else if (checked) 1f
        else -1f
    )

    val animatedThumbWidth by animateDpAsState(
        targetValue = if (isPressed || isDragged) {
            WindowsToggleSwitchDefaults.ThumbExtendedSize.width
        } else {
            WindowsToggleSwitchDefaults.ThumbSize.width
        }
    )

    val animatedThumbHeight by animateDpAsState(
        targetValue = if (isPressed || isDragged) {
            WindowsToggleSwitchDefaults.ThumbExtendedSize.height
        } else {
            WindowsToggleSwitchDefaults.ThumbSize.height
        }
    )

    val animatedTrackColor by animateColorAsState(
        targetValue = when {
            !enabled && checked -> colors.disabledTrackColor
            !enabled && !checked -> colors.disabledUncheckedTrackColor
            enabled && (isPressed || isDragged) && checked -> colors.pressedTrackColor
            enabled && (isPressed || isDragged) && !checked -> colors.pressedUncheckedTrackColor
            enabled && isHovered && checked -> colors.hoveredTrackColor
            enabled && isHovered && !checked -> colors.hoveredUncheckedTrackColor
            enabled && checked -> colors.trackColor
            enabled && !checked -> colors.uncheckedTrackColor
            else -> throw Exception("Invalid track color state!")
        },
    )

    val animatedTrackBorderColor by animateColorAsState(
        targetValue = when {
            !enabled && checked -> colors.disabledTrackBorderColor
            !enabled && !checked -> colors.disabledUncheckedTrackBorderColor
            enabled && (isPressed || isDragged) && checked -> colors.pressedTrackBorderColor
            enabled && (isPressed || isDragged) && !checked -> colors.pressedUncheckedTrackBorderColor
            enabled && isHovered && checked -> colors.hoveredTrackBorderColor
            enabled && isHovered && !checked -> colors.hoveredUncheckedTrackBorderColor
            enabled && checked -> colors.trackBorderColor
            enabled && !checked -> colors.uncheckedTrackBorderColor
            else -> throw Exception("Invalid track border color state!")
        },
    )

    val animatedThumbColor by animateColorAsState(
        targetValue = when {
            !enabled && checked -> colors.disabledThumbColor
            !enabled && !checked -> colors.disabledUncheckedThumbColor
            enabled && (isPressed || isDragged) && checked -> colors.pressedThumbColor
            enabled && (isPressed || isDragged) && !checked -> colors.pressedUncheckedThumbColor
            enabled && isHovered && checked -> colors.hoveredThumbColor
            enabled && isHovered && !checked -> colors.hoveredUncheckedThumbColor
            enabled && checked -> colors.thumbColor
            enabled && !checked -> colors.uncheckedThumbColor
            else -> throw Exception("Invalid thumb color state!")
        },
    )

    val thumbBorderColorOrBrush by remember {
        derivedStateOf {
            when {
                !enabled && checked -> colors.disabledThumbBorderColor
                !enabled && !checked -> colors.disabledUncheckedThumbBorderColor
                enabled && (isPressed || isDragged) && checked -> colors.pressedThumbBorderBrush
                enabled && (isPressed || isDragged) && !checked -> colors.pressedUncheckedThumbBorderColor
                enabled && isHovered && checked -> colors.hoveredThumbBorderBrush
                enabled && isHovered && !checked -> colors.hoveredUncheckedThumbBorderColor
                enabled && checked -> colors.thumbBorderBrush
                enabled && !checked -> colors.uncheckedThumbBorderColor
                else -> throw Exception("Invalid thumb color or brush state!")
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = if (alignTextToStart) Alignment.End else Alignment.Start
    ) {
        // Header (if available)
        if (header != null) {
            Box(
                modifier = Modifier
                    .padding(
                        start = if (alignTextToStart) 0.dp else 4.dp,
                        end = if (alignTextToStart) 4.dp else 0.dp,
                        bottom = 7.dp,
                    )
            ) {
                CompositionLocalProvider(
                    LocalTextStyle provides WindowsTheme.typography.body.copy(
                        color = WindowsTheme.colorScheme.fillTextPrimary,
                        textAlign = if (alignTextToStart) TextAlign.End else TextAlign.Start,
                    ),
                    content = { header.invoke() },
                )
            }
        }

        // Switch with Text (if available)
        Row(
            modifier = Modifier
                .focusBorder(
                    interactionSource = interactionSource,
                    shape = if (header != null || text != null) FocusBorderDefaults.Shape
                    else trackShape
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    onClick = { onCheckedChange(!checked) },
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Text (if aligned to start)
            if (alignTextToStart && text != null) paddedText()

            // Switch
            Column(
                modifier = Modifier
                    .padding(vertical = if (text != null) 6.dp else 0.dp)
                    .padding(
                        start = if (!alignTextToStart && text != null) 4.dp else 0.dp,
                        end = if (alignTextToStart && text != null) 4.dp else 0.dp,
                    )
                    .size(WindowsToggleSwitchDefaults.ToggleSize)
                    .clip(trackShape)
                    .drawBehind {
                        val outline = trackShape.createOutline(size, layoutDirection, density)
                        drawOutline(
                            outline = outline,
                            color = animatedTrackColor
                        )
                        drawOutline(
                            outline = outline,
                            color = animatedTrackBorderColor,
                            style = Stroke(width = 1.dp.toPx()),
                        )
                    }
                    .pointerInput(enabled) {
                        if (enabled) {
                            detectHorizontalDragGestures(
                                onDragStart = { offset -> currentDragOffsetX = offset.x },
                                onHorizontalDrag = { _, delta ->
                                    currentDragOffsetX?.let { safeOffsetX ->
                                        currentDragOffsetX = safeOffsetX + delta
                                    }
                                },
                                onDragEnd = {
                                    onCheckedChange(thumbDragProgress > 0.5f)
                                    currentDragOffsetX = null
                                },
                                onDragCancel = {
                                    onCheckedChange(thumbDragProgress > 0.5f)
                                    currentDragOffsetX = null
                                }
                            )
                        }
                    },
                horizontalAlignment = thumbAlignment,
                verticalArrangement = Arrangement.Center,
            ) {
                // Thumb
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(width = animatedThumbWidth, height = animatedThumbHeight)
                        .drawBehind {
                            val outline = thumbShape.createOutline(size, layoutDirection, density)

                            drawOutline(
                                outline = outline,
                                color = animatedThumbColor,
                            )

                            when (thumbBorderColorOrBrush) {
                                is Color -> {
                                    drawOutline(
                                        outline = outline,
                                        color = (thumbBorderColorOrBrush as Color),
                                        style = Stroke(width = 1.dp.toPx())
                                    )
                                }

                                is Brush -> {
                                    drawOutline(
                                        outline = outline,
                                        brush = (thumbBorderColorOrBrush as Brush),
                                        style = Stroke(width = 1.dp.toPx())
                                    )
                                }
                            }
                        }
                )
            }

            // Text (if aligned to end)
            if (!alignTextToStart && text != null) paddedText()
        }
    }
}

@Immutable
data class WindowsToggleSwitchColors(
    val trackColor: Color,
    val trackBorderColor: Color,
    val thumbColor: Color,
    val thumbBorderBrush: Brush,
    val uncheckedTrackColor: Color,
    val uncheckedTrackBorderColor: Color,
    val uncheckedThumbColor: Color,
    val uncheckedThumbBorderColor: Color,
    val hoveredTrackColor: Color,
    val hoveredTrackBorderColor: Color,
    val hoveredThumbColor: Color,
    val hoveredThumbBorderBrush: Brush,
    val hoveredUncheckedTrackColor: Color,
    val hoveredUncheckedTrackBorderColor: Color,
    val hoveredUncheckedThumbColor: Color,
    val hoveredUncheckedThumbBorderColor: Color,
    val pressedTrackColor: Color,
    val pressedTrackBorderColor: Color,
    val pressedThumbColor: Color,
    val pressedThumbBorderBrush: Brush,
    val pressedUncheckedTrackColor: Color,
    val pressedUncheckedTrackBorderColor: Color,
    val pressedUncheckedThumbColor: Color,
    val pressedUncheckedThumbBorderColor: Color,
    val disabledTrackColor: Color,
    val disabledTrackBorderColor: Color,
    val disabledThumbColor: Color,
    val disabledThumbBorderColor: Color,
    val disabledUncheckedTrackColor: Color,
    val disabledUncheckedTrackBorderColor: Color,
    val disabledUncheckedThumbColor: Color,
    val disabledUncheckedThumbBorderColor: Color,
)

object WindowsToggleSwitchDefaults {
    val ThumbSize = DpSize(width = 12.dp, height = 12.dp)
    val ThumbExtendedSize = DpSize(width = 17.dp, height = 14.dp)
    val ToggleSize = DpSize(width = 38.dp, height = 18.dp)

    @Composable
    fun colors(
        trackColor: Color = WindowsTheme.colorScheme.fillAccentDefault,
        trackBorderColor: Color = WindowsTheme.colorScheme.fillAccentDefault,
        thumbColor: Color = WindowsTheme.colorScheme.fillTextOnAccentPrimary,
        thumbBorderBrush: Brush = WindowsTheme.colorScheme.elevationCircle,
        uncheckedTrackColor: Color = WindowsTheme.colorScheme.fillControlAltSecondary,
        uncheckedTrackBorderColor: Color = WindowsTheme.colorScheme.strokeControlStrongDefault,
        uncheckedThumbColor: Color = WindowsTheme.colorScheme.fillTextSecondary,
        uncheckedThumbBorderColor: Color = Color.Transparent,
        hoveredTrackColor: Color = WindowsTheme.colorScheme.fillAccentSecondary,
        hoveredTrackBorderColor: Color = WindowsTheme.colorScheme.fillAccentSecondary,
        hoveredThumbColor: Color = WindowsTheme.colorScheme.fillTextOnAccentPrimary,
        hoveredThumbBorderBrush: Brush = WindowsTheme.colorScheme.elevationCircle,
        hoveredUncheckedTrackColor: Color = WindowsTheme.colorScheme.fillControlAltTertiary,
        hoveredUncheckedTrackBorderColor: Color = WindowsTheme.colorScheme.strokeControlStrongDefault,
        hoveredUncheckedThumbColor: Color = WindowsTheme.colorScheme.fillTextSecondary,
        hoveredUncheckedThumbBorderColor: Color = Color.Transparent,
        pressedTrackColor: Color = WindowsTheme.colorScheme.fillAccentTertiary,
        pressedTrackBorderColor: Color = WindowsTheme.colorScheme.fillAccentTertiary,
        pressedThumbColor: Color = WindowsTheme.colorScheme.fillTextOnAccentPrimary,
        pressedThumbBorderBrush: Brush = WindowsTheme.colorScheme.elevationCircle,
        pressedUncheckedTrackColor: Color = WindowsTheme.colorScheme.fillControlAltQuaternary,
        pressedUncheckedTrackBorderColor: Color = WindowsTheme.colorScheme.strokeControlStrongDefault,
        pressedUncheckedThumbColor: Color = WindowsTheme.colorScheme.fillTextSecondary,
        pressedUncheckedThumbBorderColor: Color = Color.Transparent,
        disabledTrackColor: Color = WindowsTheme.colorScheme.fillAccentDisabled,
        disabledTrackBorderColor: Color = WindowsTheme.colorScheme.fillAccentDisabled,
        disabledThumbColor: Color = WindowsTheme.colorScheme.fillTextOnAccentDisabled,
        disabledThumbBorderColor: Color = Color.Transparent,
        disabledUncheckedTrackColor: Color = WindowsTheme.colorScheme.fillControlAltDisabled,
        disabledUncheckedTrackBorderColor: Color = WindowsTheme.colorScheme.strokeControlStrongDisabled,
        disabledUncheckedThumbColor: Color = WindowsTheme.colorScheme.fillTextDisabled,
        disabledUncheckedThumbBorderColor: Color = Color.Transparent,
    ) = WindowsToggleSwitchColors(
        trackColor = trackColor,
        trackBorderColor = trackBorderColor,
        thumbColor = thumbColor,
        thumbBorderBrush = thumbBorderBrush,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedTrackBorderColor = uncheckedTrackBorderColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedThumbBorderColor = uncheckedThumbBorderColor,
        hoveredTrackColor = hoveredTrackColor,
        hoveredTrackBorderColor = hoveredTrackBorderColor,
        hoveredThumbColor = hoveredThumbColor,
        hoveredThumbBorderBrush = hoveredThumbBorderBrush,
        hoveredUncheckedTrackColor = hoveredUncheckedTrackColor,
        hoveredUncheckedTrackBorderColor = hoveredUncheckedTrackBorderColor,
        hoveredUncheckedThumbColor = hoveredUncheckedThumbColor,
        hoveredUncheckedThumbBorderColor = hoveredUncheckedThumbBorderColor,
        pressedTrackColor = pressedTrackColor,
        pressedTrackBorderColor = pressedTrackBorderColor,
        pressedThumbColor = pressedThumbColor,
        pressedThumbBorderBrush = pressedThumbBorderBrush,
        pressedUncheckedTrackColor = pressedUncheckedTrackColor,
        pressedUncheckedTrackBorderColor = pressedUncheckedTrackBorderColor,
        pressedUncheckedThumbColor = pressedUncheckedThumbColor,
        pressedUncheckedThumbBorderColor = pressedUncheckedThumbBorderColor,
        disabledTrackColor = disabledTrackColor,
        disabledTrackBorderColor = disabledTrackBorderColor,
        disabledThumbColor = disabledThumbColor,
        disabledThumbBorderColor = disabledThumbBorderColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor,
        disabledUncheckedTrackBorderColor = disabledUncheckedTrackBorderColor,
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedThumbBorderColor = disabledUncheckedThumbBorderColor,
    )
}

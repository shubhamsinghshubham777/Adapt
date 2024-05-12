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
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import design.adapt.LocalTextStyle
import design.adapt.dropShadow

@Composable
fun WindowsSlider(
    value: Float,
    onValueChange: (value: Float) -> Unit,
    bufferProgress: Float = 1f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: WindowsSliderColors = WindowsSliderDefaults.colors(),
    trackShape: Shape = WindowsSliderDefaults.TrackShape,
    thumbShape: Shape = WindowsSliderDefaults.ThumbShape,
    header: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val density = LocalDensity.current

    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()

    val animatedBufferedTrackFillColor by animateColorAsState(
        targetValue = if (enabled) colors.bufferedTrackFillColor
        else colors.disabledBufferedTrackFillColor,
    )
    val animatedFilledTrackColor by animateColorAsState(
        targetValue = if (enabled) colors.filledTrackColor else colors.disabledFilledTrackColor
    )
    val animatedThumbColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledThumbColor
            isPressed -> colors.pressedThumbColor
            isHovered -> colors.hoveredThumbColor
            else -> colors.thumbColor
        }
    )

    val animatedThumbSize by animateDpAsState(
        targetValue = when {
            isPressed || isDragged -> WindowsSliderDefaults.ThumbPressedSize
            isHovered -> WindowsSliderDefaults.ThumbHoveredSize
            else -> WindowsSliderDefaults.ThumbRestSize
        },
    )

    var sliderWidthPx by remember { mutableFloatStateOf(0f) }
    var dragOffsetX by remember { mutableFloatStateOf(0f) }
    val draggableState = rememberDraggableState { delta -> dragOffsetX += delta }
    val dragPercent by remember(value) {
        derivedStateOf {
            if (sliderWidthPx == 0f) return@derivedStateOf value
            else (dragOffsetX / sliderWidthPx).coerceIn(0f, 1f)
        }
    }

    val thumbHorizontalAlignmentBias = remember(value) {
        lerp(
            start = -1f,
            stop = 1f,
            fraction = value
        )
    }

    LaunchedEffect(value, bufferProgress) {
        check(value in 0f..1f) { "Slider's value ($value) must be between 0f and 1f!" }
        check(bufferProgress in 0f..1f) {
            "Slider's bufferProgress value ($bufferProgress) must be between 0f and 1f!"
        }
    }

    LaunchedEffect(dragPercent, value) {
        if (dragPercent != value) onValueChange(dragPercent)
    }

    Column(modifier = modifier) {
        header?.let { safeHeader ->
            Box(modifier = Modifier.padding(start = 4.dp, end = 7.dp)) {
                CompositionLocalProvider(
                    LocalTextStyle provides WindowsTheme.typography.body.copy(
                        color = WindowsTheme.colorScheme.fillTextPrimary
                    ),
                    content = { safeHeader() }
                )
            }
        }

        Box(
            modifier = Modifier
                .focusBorder(interactionSource = interactionSource)
                .padding(horizontal = 4.dp, vertical = 6.dp)
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    onDragStarted = { offset -> dragOffsetX = offset.x }
                ),
            contentAlignment = BiasAlignment(
                horizontalBias = thumbHorizontalAlignmentBias,
                verticalBias = 0f,
            )
        ) {
            // Track
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(
                        minWidth = WindowsSliderDefaults.TrackSize.width,
                        minHeight = WindowsSliderDefaults.TrackSize.height
                    )
                    .clip(trackShape)
                    .drawBehind {
                        val outline = trackShape.createOutline(size, layoutDirection, density)
                        val lineOffsetStart = Offset(x = 0f, y = size.height / 2)

                        // Base
                        drawOutline(
                            outline = outline,
                            color = animatedBufferedTrackFillColor,
                        )

                        // Buffer border
                        drawOutline(
                            outline = outline,
                            color = colors.bufferedTrackProgressColor,
                            style = Stroke(width = WindowsSliderDefaults.BufferedTrackHeight.toPx())
                        )

                        // Buffer
                        drawLine(
                            start = lineOffsetStart,
                            end = Offset(x = size.width * bufferProgress, y = size.height / 2),
                            color = colors.bufferedTrackProgressColor,
                            strokeWidth = WindowsSliderDefaults.BufferedTrackHeight.toPx(),
                            cap = StrokeCap.Round,
                        )

                        // Progress
                        drawLine(
                            start = lineOffsetStart,
                            end = Offset(x = size.width * value, y = size.height / 2),
                            color = animatedFilledTrackColor,
                            strokeWidth = size.height,
                        )
                    }
                    .onPlaced { coordinates ->
                        val localWidth = coordinates.size.width.toFloat()
                        if (sliderWidthPx != localWidth) {
                            sliderWidthPx = localWidth
                            dragOffsetX = sliderWidthPx * value
                        }
                    },
            )

            // Thumb
            Box(
                modifier = Modifier
                    .size(WindowsSliderDefaults.ThumbBaseSize)
                    .dropShadow(
                        shape = thumbShape,
                        color = colors.thumbBorderColor,
                        offsetX = -WindowsSliderDefaults.ThumbBorderSize / 2,
                        offsetY = -WindowsSliderDefaults.ThumbBorderSize / 2,
                        spread = WindowsSliderDefaults.ThumbBorderSize,
                    )
                    .clip(thumbShape)
                    .drawBehind {
                        val thumbOutline = thumbShape.createOutline(size, layoutDirection, density)
                        val thumbSize = Size(
                            width = animatedThumbSize.toPx(),
                            height = animatedThumbSize.toPx()
                        )
                        // Base
                        drawOutline(outline = thumbOutline, color = colors.thumbBaseColor)
                        // Thumb
                        drawCircle(radius = thumbSize.minDimension / 2f, color = animatedThumbColor)
                    }
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = enabled,
                        onClick = {}
                    )
            )
        }
    }
}

@Immutable
data class WindowsSliderColors(
    val filledTrackColor: Color,
    val bufferedTrackFillColor: Color,
    val bufferedTrackProgressColor: Color,
    val bufferedTrackBorderColor: Color,
    val thumbColor: Color,
    val thumbBaseColor: Color,
    val thumbBorderColor: Color,
    val tickColor: Color,
    val hoveredThumbColor: Color,
    val pressedThumbColor: Color,
    val disabledBufferedTrackFillColor: Color,
    val disabledFilledTrackColor: Color,
    val disabledThumbColor: Color,
)

object WindowsSliderDefaults {
    val TrackSize = DpSize(width = 112.dp, height = 4.dp)
    val BufferedTrackHeight = 2.dp
    val ThumbBaseSize = 20.dp
    val ThumbRestSize = 12.dp
    val ThumbHoveredSize = 14.dp
    val ThumbPressedSize = 10.dp
    val TrackShape = CircleShape
    val ThumbShape = CircleShape
    val ThumbBorderSize = 2.dp

    @Composable
    fun colors(
        filledTrackColor: Color = WindowsTheme.colorScheme.fillAccentDefault,
        bufferedTrackFillColor: Color = WindowsTheme.colorScheme.fillControlAltTertiary,
        bufferedTrackProgressColor: Color = WindowsTheme.colorScheme.fillControlStrongDefault,
        bufferedTrackBorderColor: Color = WindowsTheme.colorScheme.fillControlStrongDefault,
        thumbColor: Color = WindowsTheme.colorScheme.fillAccentDefault,
        thumbBaseColor: Color = WindowsTheme.colorScheme.fillControlSolidDefault,
        thumbBorderColor: Color = WindowsTheme.colorScheme.strokeControlDefault,
        tickColor: Color = WindowsTheme.colorScheme.strokeControlStrongDefault,
        hoveredThumbColor: Color = WindowsTheme.colorScheme.fillAccentSecondary,
        pressedThumbColor: Color = WindowsTheme.colorScheme.fillAccentTertiary,
        disabledBufferedTrackFillColor: Color = bufferedTrackFillColor,
        disabledFilledTrackColor: Color = WindowsTheme.colorScheme.fillAccentDisabled,
        disabledThumbColor: Color = WindowsTheme.colorScheme.fillAccentDisabled,
    ) = WindowsSliderColors(
        bufferedTrackFillColor = bufferedTrackFillColor,
        filledTrackColor = filledTrackColor,
        bufferedTrackProgressColor = bufferedTrackProgressColor,
        bufferedTrackBorderColor = bufferedTrackBorderColor,
        thumbColor = thumbColor,
        thumbBaseColor = thumbBaseColor,
        thumbBorderColor = thumbBorderColor,
        tickColor = tickColor,
        hoveredThumbColor = hoveredThumbColor,
        pressedThumbColor = pressedThumbColor,
        disabledBufferedTrackFillColor = disabledBufferedTrackFillColor,
        disabledFilledTrackColor = disabledFilledTrackColor,
        disabledThumbColor = disabledThumbColor,
    )
}

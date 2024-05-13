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

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import design.adapt.LocalPlatform
import design.adapt.Platform
import design.adapt.dropShadow
import design.adapt.innerShadow
import design.adapt.rememberScreenSizeInfo
import kotlinx.coroutines.launch

@Composable
fun CupertinoSlider(
    value: Float,
    onValueChange: (value: Float) -> Unit,
    modifier: Modifier = Modifier,
    steps: Int = 0,
    enabled: Boolean = true,
    trackShape: Shape = CupertinoSliderDefaults.TrackShape,
    thumbShape: Shape = CupertinoSliderDefaults.ThumbShape,
    stepShape: Shape = CupertinoSliderDefaults.TickShape,
    colors: CupertinoSliderColors = CupertinoSliderDefaults.colors(),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current
    val screenWidthPx = rememberScreenSizeInfo().widthPx

    /**
     * Used to make the user's dragging stick to a particular step for a set amount.
     *
     * For example, if the value of dragSlop is 0.025, the step is at 0.2, and the user is dragging
     * from 0.09, then the drag thumb will stick to 0.2 for all values between 0.175 and 0.225.
     *
     * By default, its value depends on the screen width to allow for similar snapping experience
     * for all screen sizes.
     */
    val dragSlop = (16f / screenWidthPx).coerceAtMost(DEFAULT_DRAG_SLOP)

    val coroutineScope = rememberCoroutineScope()
    val isPressed by interactionSource.collectIsPressedAsState()

    val trackColor = remember(enabled, colors) {
        if (enabled) colors.trackColor else colors.disabledTrackColor
    }
    val filledTrackColor = remember(enabled, colors) {
        if (enabled) colors.filledTrackColor else colors.disabledFilledTrackColor
    }
    val thumbColor = remember(enabled, colors, isPressed) {
        when {
            !enabled -> colors.disabledThumbColor
            isPressed -> colors.pressedThumbColor
            else -> colors.thumbColor
        }
    }
    val thumbBorderColor = remember(enabled, colors, isPressed) {
        when {
            !enabled -> colors.disabledThumbBorderColor
            isPressed -> colors.pressedThumbBorderColor
            else -> colors.thumbBorderColor
        }
    }
    val stepColor = remember(enabled, colors) {
        if (enabled) colors.stepColor else colors.disabledStepColor
    }

    val stepPercentages = remember(steps) {
        List(steps) { index ->
            if (steps == 1) 0.5f
            else index.toFloat() / (steps - 1).toFloat()
        }
    }
    val stepPercentageRanges by remember(dragSlop) {
        derivedStateOf {
            stepPercentages.map { percentage ->
                percentage.minus(dragSlop)..percentage.plus(dragSlop)
            }
        }
    }

    var trackWidth by remember { mutableFloatStateOf(0f) }
    var dragOffsetX by remember { mutableFloatStateOf(0f) }

    val draggableState = rememberDraggableState { delta -> dragOffsetX += delta }
    val adjustedHorizontalBiasFraction by remember(stepPercentageRanges) {
        derivedStateOf {
            val dragPercent = if (trackWidth == 0f) value
            else (dragOffsetX / trackWidth).coerceIn(0f, 1f)

            val index = stepPercentageRanges.indexOfLast { range -> range.contains(dragPercent) }
            return@derivedStateOf if (index != -1) stepPercentages[index] else dragPercent
        }
    }
    val thumbHorizontalAlignmentBias = lerp(
        start = -1f,
        stop = 1f,
        fraction = value
    )

    LaunchedEffect(adjustedHorizontalBiasFraction) {
        if (adjustedHorizontalBiasFraction != value) {
            onValueChange(adjustedHorizontalBiasFraction)
        }
    }

    LaunchedEffect(value, steps) {
        check(value in 0f..1f) {
            "Slider's value ($value) should only exist between 0f and 1f!"
        }
        check(steps >= 0) { "Slider's steps cannot be negative!" }
    }

    Row(
        modifier = modifier.graphicsLayer { alpha = if (enabled) 1f else 0.5f },
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.invoke()
        Box(
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(CupertinoSliderDefaults.TrackSize.width)
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    interactionSource = interactionSource,
                    enabled = enabled,
                    onDragStarted = { offset -> dragOffsetX = offset.x },
                )
                .pointerInput(enabled) {
                    if (enabled) {
                        detectTapGestures { offset ->
                            coroutineScope.launch {
                                Animatable(dragOffsetX).animateTo(targetValue = offset.x) {
                                    dragOffsetX = this.value
                                }
                            }
                        }
                    }
                },
            contentAlignment = BiasAlignment(
                horizontalBias = thumbHorizontalAlignmentBias,
                verticalBias = 0f,
            ),
        ) {
            // Track
            Box(
                modifier = Modifier
                    .height(CupertinoSliderDefaults.TrackSize.height)
                    .clip(trackShape)
                    .onGloballyPositioned { coordinates ->
                        val localWidth = coordinates.size.width.toFloat()
                        if (trackWidth != localWidth) {
                            trackWidth = coordinates.size.width.toFloat()
                            dragOffsetX = trackWidth * value
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .drawBehind { drawRect(color = trackColor) }
                        .trackInnerShadow(shape = trackShape)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(value)
                        .fillMaxHeight()
                        .drawBehind { drawRect(color = filledTrackColor) }
                )
            }

            // Steps
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // `step == 1` is a special case, because we want the single step to show up in the
                // middle but the horizontalArrangement of this Row places it to the start. That's why
                // we need at least 3 steps for the desired single step to show up in the center.
                val adjustedSteps = if (steps == 1) 3 else steps

                repeat(adjustedSteps) { index ->
                    val localPercent = stepPercentages[if (steps == 1) 0 else index]
                    val isFilled = value >= localPercent
                    val showStep = if (steps == 1) index == 1 else true

                    Box(
                        modifier = Modifier
                            .graphicsLayer { alpha = if (showStep) 1f else 0f }
                            .size(CupertinoSliderDefaults.StepSize)
                            .clip(stepShape)
                            .drawBehind {
                                val outline =
                                    stepShape.createOutline(size, layoutDirection, density)
                                drawOutline(
                                    outline = outline,
                                    color = if (isFilled) filledTrackColor else stepColor
                                )
                            }
                    )
                }
            }

            // Thumb
            Box(
                modifier = Modifier
                    .size(
                        width = if (steps > 0) CupertinoSliderDefaults.TickedThumbWidth
                        else CupertinoSliderDefaults.ThumbWidth,
                        height = CupertinoSliderDefaults.ThumbHeight
                    )
                    .thumbDropShadow(shape = thumbShape, enabled = enabled)
                    .drawBehind {
                        val outline = thumbShape.createOutline(size, layoutDirection, density)
                        drawOutline(outline = outline, color = thumbColor)
                        drawOutline(
                            outline = outline,
                            color = thumbBorderColor,
                            style = Stroke(width = 0.5.dp.toPx())
                        )
                    }
            )
        }
        trailingIcon?.invoke()
    }
}

private fun Modifier.trackInnerShadow(shape: Shape) = composed {
    if (LocalPlatform.current == Platform.MacOS) {
        then(
            Modifier
                .innerShadow(
                    shape = shape,
                    blur = 2.dp,
                    offsetY = 1.dp,
                    color = Color.Black.copy(alpha = 0.02f),
                )
                .innerShadow(
                    shape = shape,
                    blur = 2.dp,
                    color = Color.Black.copy(alpha = 0.03f),
                )
                .innerShadow(
                    shape = shape,
                    blur = 2.dp,
                    color = Color.Black.copy(alpha = 0.04f),
                )
        )
    } else this
}

private fun Modifier.thumbDropShadow(shape: Shape, enabled: Boolean) = composed {
    when {
        !enabled -> this
        LocalPlatform.current.isDesktop -> then(
            Modifier
                .dropShadow(
                    shape = shape,
                    blur = 0.25.dp,
                    offsetY = 0.25.dp,
                    color = Color.Black.copy(alpha = 0.15f),
                )
                .dropShadow(
                    shape = shape,
                    blur = 0.75.dp,
                    offsetY = 1.dp,
                    color = Color.Black.copy(alpha = 0.05f),
                )
        )
        else -> then(
            Modifier
                .dropShadow(
                    shape = shape,
                    blur = 4.dp,
                    offsetY = 0.5.dp,
                    color = Color.Black.copy(alpha = 0.12f),
                )
                .dropShadow(
                    shape = shape,
                    blur = 13.dp,
                    offsetY = 6.dp,
                    color = Color.Black.copy(alpha = 0.12f),
                )
        )
    }
}

@Immutable
data class CupertinoSliderColors(
    val trackColor: Color,
    val filledTrackColor: Color,
    val thumbColor: Color,
    val thumbBorderColor: Color,
    val stepColor: Color,
    val pressedThumbColor: Color,
    val pressedThumbBorderColor: Color,
    val disabledTrackColor: Color,
    val disabledFilledTrackColor: Color,
    val disabledThumbColor: Color,
    val disabledThumbBorderColor: Color,
    val disabledStepColor: Color,
)

object CupertinoSliderDefaults {
    val TrackSize = DpSize(width = 150.dp, height = 4.dp)
    val ThumbWidth = 20.dp
    val TickedThumbWidth = 8.dp
    val StepSize = DpSize(width = 2.dp, height = 8.dp)
    val ThumbHeight = 20.dp
    val TrackShape = CircleShape
    val ThumbShape = CircleShape
    val TickShape = CircleShape

    @Composable
    fun colors(
        trackColor: Color = onPlatform(
            iOS = IOSTheme.colorScheme.fillSecondary,
            macOS = Color.Black.copy(alpha = 0.05f),
        ),
        filledTrackColor: Color = onPlatform(
            iOS = IOSTheme.colorScheme.systemBlue,
            macOS = MacOSTheme.colorScheme.systemBlue,
        ),
        thumbColor: Color = onPlatform(
            iOS = IOSTheme.colorScheme.systemWhite,
            macOS = MacOSTheme.colorScheme.systemWhite,
        ),
        thumbBorderColor: Color = onPlatform(
            iOS = IOSTheme.colorScheme.systemBlack.copy(alpha = 0.02f),
            macOS = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.02f),
        ),
        stepColor: Color = Color(0xFFC9C9C7),
        pressedThumbColor: Color = thumbColor,
        pressedThumbBorderColor: Color = thumbBorderColor,
        disabledTrackColor: Color = trackColor,
        disabledFilledTrackColor: Color = filledTrackColor,
        disabledThumbColor: Color = thumbColor,
        disabledThumbBorderColor: Color = thumbBorderColor,
        disabledStepColor: Color = stepColor,
    ) = CupertinoSliderColors(
        trackColor = trackColor,
        filledTrackColor = filledTrackColor,
        thumbColor = thumbColor,
        thumbBorderColor = thumbBorderColor,
        stepColor = stepColor,
        pressedThumbColor = pressedThumbColor,
        pressedThumbBorderColor = pressedThumbBorderColor,
        disabledTrackColor = disabledTrackColor,
        disabledFilledTrackColor = disabledFilledTrackColor,
        disabledThumbColor = disabledThumbColor,
        disabledThumbBorderColor = disabledThumbBorderColor,
        disabledStepColor = disabledStepColor,
    )

    @Composable
    private fun <T> onPlatform(iOS: T, macOS: T, other: T = iOS): T = when (LocalPlatform.current) {
        Platform.IOS -> iOS
        Platform.MacOS -> macOS
        else -> other
    }
}

private const val DEFAULT_DRAG_SLOP = 0.025f

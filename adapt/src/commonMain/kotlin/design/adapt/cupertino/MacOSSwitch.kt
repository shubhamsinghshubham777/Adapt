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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import design.adapt.animateHorizontalAlignmentAsState
import design.adapt.dropShadow
import design.adapt.innerShadow

@Composable
fun MacOSSwitch(
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trackShape: Shape = CircleShape,
    thumbShape: Shape = CircleShape,
    colors: MacOSSwitchColors = MacOSSwitchDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val density = LocalDensity.current
    var dragOffsetX by remember { mutableStateOf<Float?>(null) }

    /**
     * Gives first preference to whether the user has dragged the thumb over half the width of the
     * toggle, but if the user is not dragging, then it prefers the provided [checked] value.
     */
    val isDraggedEnoughOrChecked by remember(checked) {
        derivedStateOf {
            dragOffsetX?.let { safeDragOffsetX ->
                val widthPx = with(density) { MacOSSwitchDefaults.SwitchSize.width.toPx() }
                return@derivedStateOf safeDragOffsetX > widthPx / 2
            }
            return@derivedStateOf checked
        }
    }

    val thumbAlignment by animateHorizontalAlignmentAsState(
        targetBiasValue = if (isDraggedEnoughOrChecked) 1f else -1f
    )

    val animatedTrackColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledTrackColor
            isDraggedEnoughOrChecked -> colors.trackColor
            else -> colors.uncheckedTrackColor
        },
    )

    val animatedThumbColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledThumbColor
            isDraggedEnoughOrChecked -> colors.thumbColor
            else -> colors.uncheckedThumbColor
        },
    )

    val animatedThumbBorderColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledThumbBorderColor
            isDraggedEnoughOrChecked -> colors.thumbBorderColor
            else -> colors.uncheckedThumbBorderColor
        },
    )

    Column(
        modifier = modifier
            .defaultMinSize(
                minWidth = MacOSSwitchDefaults.SwitchSize.width,
                minHeight = MacOSSwitchDefaults.SwitchSize.height
            )
            .clip(trackShape)
            .switchInnerShadows(shape = trackShape)
            .drawBehind {
                val outline = trackShape.createOutline(size, layoutDirection, density)
                drawOutline(
                    outline = outline,
                    color = animatedTrackColor,
                )
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = { onCheckedChange(!checked) },
            )
            .pointerInput(enabled) {
                if (enabled) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset -> dragOffsetX = offset.x },
                        onHorizontalDrag = { _, delta ->
                            dragOffsetX?.let { safeOffsetX -> dragOffsetX = safeOffsetX + delta }
                        },
                        onDragEnd = {
                            onCheckedChange(isDraggedEnoughOrChecked)
                            dragOffsetX = null
                        },
                        onDragCancel = {
                            onCheckedChange(isDraggedEnoughOrChecked)
                            dragOffsetX = null
                        },
                    )
                }
            },
        horizontalAlignment = thumbAlignment,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .padding(0.5.dp)
                .size(MacOSSwitchDefaults.ThumbSize)
                .clip(thumbShape)
                .dropShadow(
                    shape = thumbShape,
                    offsetY = 0.25.dp,
                    blur = 0.5.dp,
                    spread = 0.1.dp,
                    color = Color.Black.copy(alpha = 0.12f),
                )
                .drawBehind {
                    val outline = thumbShape.createOutline(size, layoutDirection, density)
                    drawOutline(
                        outline = outline,
                        color = animatedThumbColor,
                    )
                    drawOutline(
                        outline = outline,
                        color = animatedThumbBorderColor,
                        style = Stroke(width = 0.5.dp.toPx())
                    )
                }
        )
    }
}

private fun Modifier.switchInnerShadows(shape: Shape) = then(
    Modifier
        .innerShadow(
            shape = shape,
            blur = 2.dp,
            spread = 2.dp,
            color = Color.Black.copy(alpha = 0.02f),
        )
        .innerShadow(
            shape = shape,
            blur = 1.5.dp,
            spread = 0.35.dp,
            color = Color.Black.copy(alpha = 0.12f),
        )
)

@Immutable
data class MacOSSwitchColors(
    val trackColor: Color,
    val thumbColor: Color,
    val thumbBorderColor: Color,
    val uncheckedTrackColor: Color,
    val uncheckedThumbColor: Color,
    val uncheckedThumbBorderColor: Color,
    val disabledTrackColor: Color,
    val disabledThumbColor: Color,
    val disabledThumbBorderColor: Color,
)

object MacOSSwitchDefaults {
    val SwitchSize = DpSize(width = 26.dp, height = 15.dp)
    val ThumbSize = DpSize(width = 13.dp, height = 13.dp)

    @Composable
    fun colors(
        trackColor: Color = Color(0xFF478CF6),
        thumbColor: Color = MacOSTheme.colorScheme.systemWhite,
        thumbBorderColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.02f),
        uncheckedTrackColor: Color = MacOSTheme.colorScheme.systemBlack.copy(alpha = 0.09f),
        uncheckedThumbColor: Color = MacOSTheme.colorScheme.systemWhite,
        uncheckedThumbBorderColor: Color = thumbBorderColor,
        disabledTrackColor: Color = uncheckedTrackColor,
        disabledThumbColor: Color = thumbColor,
        disabledThumbBorderColor: Color = thumbBorderColor,
    ) = MacOSSwitchColors(
        trackColor = trackColor,
        thumbColor = thumbColor,
        thumbBorderColor = thumbBorderColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedThumbBorderColor = uncheckedThumbBorderColor,
        disabledTrackColor = disabledTrackColor,
        disabledThumbColor = disabledThumbColor,
        disabledThumbBorderColor = disabledThumbBorderColor,
    )
}

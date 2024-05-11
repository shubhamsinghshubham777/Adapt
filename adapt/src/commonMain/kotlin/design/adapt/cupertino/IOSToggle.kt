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
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import design.adapt.animateHorizontalAlignmentAsState
import design.adapt.dropShadow

@Composable
fun IOSToggle(
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    colors: IOSToggleColors = IOSToggleDefaults.colors(),
    trackShape: Shape = CircleShape,
    thumbShape: Shape = CircleShape,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current
    val isPressed by interactionSource.collectIsPressedAsState()

    var dragOffset by remember { mutableStateOf<Offset?>(null) }
    val isDragInsideContainer by remember {
        derivedStateOf {
            dragOffset?.let { safeOffset ->
                val size = with(density) { IOSToggleDefaults.ToggleSize.toSize() }
                val bounds = Rect(Offset.Zero, Offset(x = size.width, y = size.height))
                return@derivedStateOf bounds.inflate(size.width).contains(safeOffset)
            }
            return@derivedStateOf false
        }
    }

    /**
     * Gives first preference to whether the user has dragged the thumb over half the width of the
     * toggle, but if the user is not dragging, then it prefers the provided [checked] value.
     */
    val isDraggedEnoughOrChecked by remember(checked) {
        derivedStateOf {
            dragOffset?.let { safeDragOffset ->
                val widthPx = with(density) { IOSToggleDefaults.ToggleSize.width.toPx() }
                return@derivedStateOf safeDragOffset.x > widthPx / 2
            }
            return@derivedStateOf checked
        }
    }

    val thumbAlignment by animateHorizontalAlignmentAsState(
        targetBiasValue = if (isDraggedEnoughOrChecked) 1f else -1f
    )

    val animatedTrackColor by animateColorAsState(
        targetValue = if (isDraggedEnoughOrChecked) colors.trackColor
        else colors.uncheckedTrackColor,
    )
    val animatedThumbColor by animateColorAsState(
        targetValue = if (isDraggedEnoughOrChecked) colors.thumbColor
        else colors.uncheckedThumbColor,
    )
    val animatedThumbWidthDp by animateDpAsState(
        targetValue = if (isPressed || isDragInsideContainer) IOSToggleDefaults.ExpandedThumbWidth
        else IOSToggleDefaults.ThumbWidth,
        animationSpec = tween(delayMillis = if (isPressed || isDragInsideContainer) 100 else 0),
    )

    Column(
        modifier = Modifier
            .size(IOSToggleDefaults.ToggleSize)
            .then(modifier)
            .clip(trackShape)
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
                onClick = { onCheckedChange(!checked) },
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { dragOffset = it },
                    onDrag = { _, delta ->
                        dragOffset?.let { safeDragOffset -> dragOffset = safeDragOffset + delta }
                    },
                    onDragEnd = {
                        onCheckedChange(isDraggedEnoughOrChecked)
                        dragOffset = null
                    },
                    onDragCancel = {
                        onCheckedChange(isDraggedEnoughOrChecked)
                        dragOffset = null
                    },
                )
            },
        horizontalAlignment = thumbAlignment,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .size(animatedThumbWidthDp, IOSToggleDefaults.ThumbHeight)
                .thumbDropShadow(shape = thumbShape)
                .drawBehind {
                    val outline = thumbShape.createOutline(size, layoutDirection, density)
                    drawOutline(
                        outline = outline,
                        color = animatedThumbColor,
                    )
                }
        )
    }
}

@Composable
private fun Modifier.thumbDropShadow(shape: Shape) = then(
    Modifier
        .dropShadow(
            shape = shape,
            blur = 0.dp,
            spread = 1.dp,
            color = IOSTheme.colorScheme.systemBlack.copy(alpha = 0.04f)
        )
        .dropShadow(
            shape = shape,
            offsetY = 3.dp,
            blur = 8.dp,
            color = IOSTheme.colorScheme.systemBlack.copy(alpha = 0.15f)
        )
        .dropShadow(
            shape = shape,
            offsetY = 3.dp,
            blur = 1.dp,
            color = IOSTheme.colorScheme.systemBlack.copy(alpha = 0.06f)
        )
)

@Immutable
data class IOSToggleColors(
    val trackColor: Color,
    val uncheckedTrackColor: Color,
    val thumbColor: Color,
    val uncheckedThumbColor: Color,
)

object IOSToggleDefaults {
    val ThumbWidth = 27.dp
    val ExpandedThumbWidth = ThumbWidth * 1.25f
    val ThumbHeight = 27.dp
    val ToggleSize = DpSize(51.dp, 31.dp)

    @Composable
    fun colors(
        trackColor: Color = IOSTheme.colorScheme.systemGreen,
        uncheckedTrackColor: Color = IOSTheme.colorScheme.fillSecondary,
        thumbColor: Color = IOSTheme.colorScheme.systemWhite,
        uncheckedThumbColor: Color = thumbColor,
    ) = IOSToggleColors(
        trackColor = trackColor,
        uncheckedTrackColor = uncheckedTrackColor,
        thumbColor = thumbColor,
        uncheckedThumbColor = uncheckedThumbColor,
    )
}

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

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

@Composable
fun WindowsProgressRing(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    borderStroke: BorderStroke = WindowsProgressRingDefaults.BorderMedium,
    trackColor: Color = Color.Transparent,
) {
    val startAngle = if (progress != null) -90f
    else rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = WindowsProgressRingDefaults.START_ANGLE_ANIMATION_MILLIS,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart,
        ),
    ).value

    val sweepAngle = if (progress != null) lerp(start = 0f, stop = 360f, fraction = progress)
    else rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 180f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = WindowsProgressRingDefaults.SWEEP_ANGLE_ANIMATION_MILLIS,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse,
        ),
    ).value

    BasicProgressRing(
        modifier = modifier,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        borderStroke = borderStroke,
        trackColor = trackColor,
    )
}

@Composable
private fun BasicProgressRing(
    modifier: Modifier = Modifier,
    startAngle: Float,
    sweepAngle: Float,
    borderStroke: BorderStroke,
    trackColor: Color,
) {
    Canvas(
        modifier = modifier
            .size(
                width = WindowsProgressRingDefaults.SizeMedium,
                height = WindowsProgressRingDefaults.SizeMedium,
            )
            .padding(borderStroke.width / 2),
    ) {
        drawCircle(
            color = trackColor,
            style = Stroke(width = borderStroke.width.toPx())
        )
        drawArc(
            brush = borderStroke.brush,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = borderStroke.width.toPx(),
                cap = StrokeCap.Round,
            ),
        )
    }
}

object WindowsProgressRingDefaults {
    val SizeSmall = 16.dp
    val SizeMedium = 32.dp
    val SizeLarge = 64.dp

    val BorderSmall
        @Composable get() = BorderStroke(
            width = 2.dp,
            color = WindowsTheme.colorScheme.fillAccentDefault,
        )

    val BorderMedium
        @Composable get() = BorderStroke(
            width = 4.dp,
            color = WindowsTheme.colorScheme.fillAccentDefault,
        )

    val BorderLarge
        @Composable get() = BorderStroke(
            width = 8.dp,
            color = WindowsTheme.colorScheme.fillAccentDefault,
        )

    val TrackColor @Composable get() = WindowsTheme.colorScheme.strokeControlStrongDefault

    internal const val START_ANGLE_ANIMATION_MILLIS = 700
    internal const val SWEEP_ANGLE_ANIMATION_MILLIS = START_ANGLE_ANIMATION_MILLIS * 2
}

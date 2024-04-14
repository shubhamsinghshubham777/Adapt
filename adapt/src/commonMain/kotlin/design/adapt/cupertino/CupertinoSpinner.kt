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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import design.adapt.LocalContentColor
import design.adapt.LocalPlatform
import design.adapt.LocalTextStyle
import design.adapt.Platform
import kotlinx.coroutines.delay

@Composable
fun CupertinoSpinner(
    modifier: Modifier = Modifier,
    color: Color = CupertinoSpinnerDefaults.Color,
    text: (@Composable () -> Unit)? = null,
) {
    val platformTextStyle = when (LocalPlatform.current) {
        Platform.IOS -> IOSTheme.typography.bodyRegular
        Platform.MacOS -> MacOSTheme.typography.bodyRegular
        else -> LocalTextStyle.current
    }
    var rotationAngle by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(100)
            rotationAngle = 45f  * when(rotationAngle) {
                45f * 0 -> 1
                45f * 1 -> 2
                45f * 2 -> 3
                45f * 3 -> 4
                45f * 4 -> 5
                45f * 5 -> 6
                45f * 6 -> 7
                else -> 0
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(
            modifier = modifier
                .size(
                    width = CupertinoSpinnerDefaults.sizeRegular,
                    height = CupertinoSpinnerDefaults.sizeRegular
                )
                .graphicsLayer { rotationZ = rotationAngle }
        ) {
            val width = size.width / 7.5f
            val height = size.height / 3f

            fun bar(
                rotationDegrees: Float,
                topLeft: Offset,
                color: Color,
            ) {
                rotate(rotationDegrees) {
                    drawRoundRect(
                        topLeft = topLeft,
                        color = color,
                        cornerRadius = CornerRadius(x = width, y = width),
                        size = Size(width, height),
                    )
                }
            }

            repeat(8) { index ->
                bar(
                    rotationDegrees = 45f * index,
                    topLeft = Offset(x = center.x - width / 2, y = 0f),
                    color = color.copy(
                        alpha = when (index) {
                            0 -> 1f
                            1 -> 0.15f
                            2 -> 0.27f
                            3 -> 0.39f
                            4 -> 0.51f
                            5 -> 0.63f
                            6 -> 0.75f
                            else -> 0.87f
                        }
                    ),
                )
            }
        }
        text?.let { safeText ->
            CompositionLocalProvider(
                LocalContentColor provides color,
                LocalTextStyle provides platformTextStyle,
                content = { safeText() }
            )
        }
    }
}

object CupertinoSpinnerDefaults {
    val sizeRegular = 30.dp
    val sizeSmall = 22.dp
    val Color @Composable get() = when(LocalPlatform.current) {
        Platform.IOS -> IOSTheme.colorScheme.labelSecondary
        Platform.MacOS -> MacOSTheme.colorScheme.labelSecondary
        else -> Color(0x993c3c43)
    }
}

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

package design.adapt

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual val platform: Platform = Platform.Android

/**
 * Adds a drop shadow effect to the composable.
 *
 * This modifier allows you to draw a shadow behind the composable with various customization options.
 *
 * @param shape The shape of the shadow.
 * @param color The color of the shadow.
 * @param blur The blur radius of the shadow
 * @param offsetY The shadow offset along the Y-axis.
 * @param offsetX The shadow offset along the X-axis.
 * @param spread The amount to increase the size of the shadow.
 *
 * @return A new `Modifier` with the drop shadow effect applied.
 */
actual fun Modifier.dropShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetX: Dp,
    offsetY: Dp,
    spread: Dp
): Modifier = then(
    drawBehind {
        val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

        val paint = Paint()
        paint.color = color

        if (blur.toPx() > 0) {
            paint.asFrameworkPaint().apply {
                maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
            }
        }

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetX.toPx(), offsetY.toPx())
            canvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
)

actual fun Modifier.innerShadow(
    shape: Shape,
    color: Color,
    blur: Dp,
    offsetX: Dp,
    offsetY: Dp,
    spread: Dp,
): Modifier = then(
    drawWithContent {
        drawContent()

        val rect = Rect(Offset.Zero, size)
        val paint = Paint().apply {
            this.color = color
            this.isAntiAlias = true
        }

        val shadowOutline = shape.createOutline(size, layoutDirection, this)

        drawIntoCanvas { canvas ->

            canvas.saveLayer(rect, paint)
            canvas.drawOutline(shadowOutline, paint)

            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

            if (blur.toPx() > 0) {
                frameworkPaint.maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
            }

            paint.color = Color.Black

            val spreadOffsetX =
                offsetX.toPx() + if (offsetX.toPx() < 0) -spread.toPx() else spread.toPx()
            val spreadOffsetY =
                offsetY.toPx() + if (offsetY.toPx() < 0) -spread.toPx() else spread.toPx()

            canvas.translate(spreadOffsetX, spreadOffsetY)
            canvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
)

@Composable
actual fun rememberScreenSizeInfo(): ScreenSizeInfo {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val hDp = config.screenHeightDp.dp
    val wDp = config.screenWidthDp.dp

    return remember(density, config) {
        ScreenSizeInfo(
            widthPx = with(density) { wDp.roundToPx() },
            heightPx = with(density) { hDp.roundToPx() },
            widthDp = wDp,
            heightDp = hDp,
        )
    }
}

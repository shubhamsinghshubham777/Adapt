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

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun Modifier.focusBorder(
    interactionSource: InteractionSource,
    innerColor: Color = LocalWindowsColorScheme.current.focusStrokeInner,
    outerColor: Color = LocalWindowsColorScheme.current.focusStrokeOuter,
    shape: Shape = RoundedCornerShape(7.dp),
    thickness: Dp = 3.dp,
): Modifier = composed {
    val isFocused by interactionSource.collectIsFocusedAsState()
    then(
        Modifier
            // Outer border
            .border(
                width = thickness,
                color = if (isFocused) outerColor else Color.Transparent,
                shape = shape
            )
            .padding(all = thickness * 0.75f)
            // Inner border
            .border(
                width = thickness,
                color = if (isFocused) innerColor else Color.Transparent,
                shape = shape
            )
            .padding(all = thickness)
    )
}

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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Shape
import design.adapt.cupertino.CupertinoSlider
import design.adapt.cupertino.CupertinoSliderColors
import design.adapt.cupertino.CupertinoSliderDefaults
import design.adapt.windows.WindowsSlider
import design.adapt.windows.WindowsSliderColors
import design.adapt.windows.WindowsSliderDefaults

@Composable
fun AdaptSlider(
    value: Float,
    onValueChange: (value: Float) -> Unit,
    modifier: AdaptModifier = AdaptModifier(),
    enabled: Boolean = true,
    configuration: AdapterSliderConfiguration = AdaptSliderDefaults.configuration(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    when (val platform = LocalPlatform.current) {
        // TODO(Shubham): Separate Web from Android
        Platform.Android, Platform.Web -> Slider(
            colors = configuration.android.colors,
            enabled = enabled,
            interactionSource = interactionSource,
            modifier = if (platform == Platform.Android) modifier.android else modifier.web,
            onValueChange = onValueChange,
            value = value,
        )

        Platform.IOS, Platform.MacOS -> CupertinoSlider(
            colors = if (platform == Platform.IOS) configuration.iOS.colors else configuration.macOS.colors,
            enabled = enabled,
            interactionSource = interactionSource,
            leadingIcon = if (platform == Platform.IOS) configuration.iOS.leadingIcon else configuration.macOS.leadingIcon,
            modifier = if (platform == Platform.IOS) modifier.iOS else modifier.macOS,
            onValueChange = onValueChange,
            stepShape = if (platform == Platform.IOS) configuration.iOS.stepShape else configuration.macOS.stepShape,
            steps = if (platform == Platform.IOS) configuration.iOS.steps else configuration.macOS.steps,
            thumbShape = if (platform == Platform.IOS) configuration.iOS.thumbShape else configuration.macOS.thumbShape,
            trackShape = if (platform == Platform.IOS) configuration.iOS.trackShape else configuration.macOS.trackShape,
            trailingIcon = if (platform == Platform.IOS) configuration.iOS.trailingIcon else configuration.macOS.trailingIcon,
            value = value,
        )

        Platform.Windows -> WindowsSlider(
            bufferProgress = configuration.windows.bufferProgress,
            colors = configuration.windows.colors,
            enabled = enabled,
            header = configuration.windows.header,
            interactionSource = interactionSource,
            modifier = modifier.windows,
            onValueChange = onValueChange,
            thumbShape = configuration.windows.thumbShape,
            trackShape = configuration.windows.trackShape,
            value = value,
        )
    }
}

@Immutable
data class AndroidSliderConfiguration(val colors: SliderColors)

@Immutable
data class IOSSliderConfiguration(
    val colors: CupertinoSliderColors,
    val leadingIcon: @Composable (() -> Unit)?,
    val stepShape: Shape,
    val steps: Int,
    val thumbShape: Shape,
    val trackShape: Shape,
    val trailingIcon: @Composable (() -> Unit)?,
)

@Immutable
data class MacOSSliderConfiguration(
    val colors: CupertinoSliderColors,
    val leadingIcon: @Composable (() -> Unit)?,
    val stepShape: Shape,
    val steps: Int,
    val thumbShape: Shape,
    val trackShape: Shape,
    val trailingIcon: @Composable (() -> Unit)?,
)

@Immutable
data class WindowsSliderConfiguration(
    val bufferProgress: Float,
    val colors: WindowsSliderColors,
    val header: @Composable (() -> Unit)?,
    val thumbShape: Shape,
    val trackShape: Shape,
)

@Immutable
data class AdapterSliderConfiguration(
    val android: AndroidSliderConfiguration,
    val iOS: IOSSliderConfiguration,
    val macOS: MacOSSliderConfiguration,
    val windows: WindowsSliderConfiguration,
)

object AdaptSliderDefaults {
    @Composable
    fun configuration(
        android: AndroidSliderConfiguration = AndroidSliderConfiguration(
            colors = SliderDefaults.colors(),
        ),
        iOS: IOSSliderConfiguration = IOSSliderConfiguration(
            colors = CupertinoSliderDefaults.colors(),
            leadingIcon = null,
            stepShape = CupertinoSliderDefaults.TickShape,
            steps = 0,
            thumbShape = CupertinoSliderDefaults.ThumbShape,
            trackShape = CupertinoSliderDefaults.TrackShape,
            trailingIcon = null,
        ),
        macOS: MacOSSliderConfiguration = MacOSSliderConfiguration(
            colors = CupertinoSliderDefaults.colors(),
            leadingIcon = null,
            stepShape = CupertinoSliderDefaults.TickShape,
            steps = 0,
            thumbShape = CupertinoSliderDefaults.ThumbShape,
            trackShape = CupertinoSliderDefaults.TrackShape,
            trailingIcon = null,
        ),
        windows: WindowsSliderConfiguration = WindowsSliderConfiguration(
            bufferProgress = 1f,
            colors = WindowsSliderDefaults.colors(),
            header = null,
            thumbShape = WindowsSliderDefaults.ThumbShape,
            trackShape = WindowsSliderDefaults.TrackShape,
        ),
    ) = AdapterSliderConfiguration(android = android, iOS = iOS, macOS = macOS, windows = windows)
}

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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Shape
import design.adapt.cupertino.IOSToggle
import design.adapt.cupertino.IOSToggleColors
import design.adapt.cupertino.IOSToggleDefaults
import design.adapt.cupertino.MacOSSwitch
import design.adapt.cupertino.MacOSSwitchColors
import design.adapt.cupertino.MacOSSwitchDefaults
import design.adapt.windows.WindowsToggleSwitch
import design.adapt.windows.WindowsToggleSwitchColors
import design.adapt.windows.WindowsToggleSwitchDefaults

@Composable
fun AdaptSwitch(
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    modifier: AdaptModifier = AdaptModifier(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    configuration: AdaptSwitchConfiguration = AdaptSwitchDefaults.configuration(),
) {
    when (LocalPlatform.current) {
        // TODO: Separate Web from Android here
        Platform.Android, Platform.Web -> Switch(
            checked = checked,
            colors = configuration.android.colors,
            enabled = enabled,
            interactionSource = interactionSource,
            modifier = modifier.android,
            onCheckedChange = onCheckedChange,
            thumbContent = configuration.android.thumbContent,
        )

        Platform.IOS -> IOSToggle(
            checked = checked,
            colors = configuration.iOS.colors,
            interactionSource = interactionSource,
            modifier = modifier.iOS,
            onCheckedChange = onCheckedChange,
            thumbShape = configuration.iOS.thumbShape,
            trackShape = configuration.iOS.trackShape,
        )

        Platform.MacOS -> MacOSSwitch(
            checked = checked,
            colors = configuration.macOS.colors,
            enabled = enabled,
            interactionSource = interactionSource,
            modifier = modifier.macOS,
            onCheckedChange = onCheckedChange,
            thumbShape = configuration.macOS.thumbShape,
            trackShape = configuration.macOS.trackShape,
        )

        Platform.Windows -> WindowsToggleSwitch(
            alignTextToStart = configuration.windows.alignTextToStart,
            checked = checked,
            colors = configuration.windows.colors,
            enabled = enabled,
            header = configuration.windows.header,
            interactionSource = interactionSource,
            modifier = modifier.windows,
            onCheckedChange = onCheckedChange,
            text = configuration.windows.text,
            thumbShape = configuration.windows.thumbShape,
            trackShape = configuration.windows.trackShape,
        )
    }
}

object AdaptSwitchDefaults {
    @Composable
    fun configuration(
        android: AndroidSwitchConfiguration = AndroidSwitchConfiguration(
            colors = SwitchDefaults.colors(),
            thumbContent = null,
        ),
        iOS: IOSToggleConfiguration = IOSToggleConfiguration(
            colors = IOSToggleDefaults.colors(),
            thumbShape = IOSToggleDefaults.ThumbShape,
            trackShape = IOSToggleDefaults.TrackShape,
        ),
        macOS: MacOSSwitchConfiguration = MacOSSwitchConfiguration(
            colors = MacOSSwitchDefaults.colors(),
            thumbShape = MacOSSwitchDefaults.ThumbShape,
            trackShape = MacOSSwitchDefaults.TrackShape,
        ),
        windows: WindowsToggleSwitchConfiguration = WindowsToggleSwitchConfiguration(
            alignTextToStart = false,
            colors = WindowsToggleSwitchDefaults.colors(),
            header = null,
            text = null,
            thumbShape = WindowsToggleSwitchDefaults.ThumbShape,
            trackShape = WindowsToggleSwitchDefaults.TrackShape,
        ),
    ) = AdaptSwitchConfiguration(android = android, iOS = iOS, macOS = macOS, windows = windows)
}

@Immutable
data class AdaptSwitchConfiguration(
    val android: AndroidSwitchConfiguration,
    val iOS: IOSToggleConfiguration,
    val macOS: MacOSSwitchConfiguration,
    val windows: WindowsToggleSwitchConfiguration,
)

@Immutable
data class AndroidSwitchConfiguration(
    val colors: SwitchColors,
    val thumbContent: @Composable (() -> Unit)?,
)

@Immutable
data class IOSToggleConfiguration(
    val colors: IOSToggleColors,
    val thumbShape: Shape,
    val trackShape: Shape,
)

@Immutable
data class MacOSSwitchConfiguration(
    val colors: MacOSSwitchColors,
    val thumbShape: Shape,
    val trackShape: Shape,
)

@Immutable
data class WindowsToggleSwitchConfiguration(
    val alignTextToStart: Boolean,
    val colors: WindowsToggleSwitchColors,
    val header: @Composable (() -> Unit)?,
    val text: @Composable (() -> Unit)?,
    val thumbShape: Shape,
    val trackShape: Shape,
)

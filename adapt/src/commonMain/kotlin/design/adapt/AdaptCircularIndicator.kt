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

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import design.adapt.cupertino.CupertinoSpinner
import design.adapt.cupertino.CupertinoSpinnerDefaults
import design.adapt.windows.WindowsProgressRing
import design.adapt.windows.WindowsProgressRingDefaults

@Composable
fun AdaptCircularIndicator(
    modifier: AdaptModifier = AdaptModifier(),
    color: Color = AdaptCircularIndicatorDefaults.Color,
    configuration: AdaptCircularIndicatorConfiguration = AdaptCircularIndicatorDefaults.configuration(),
) {
    when (LocalPlatform.current) {
        // TODO: Separate Web from Android here
        Platform.Android, Platform.Web -> configuration.android.progress?.let { progress ->
            CircularProgressIndicator(
                modifier = modifier.android,
                color = color,
                strokeWidth = configuration.android.strokeWidth,
                trackColor = configuration.android.trackColor,
                strokeCap = configuration.android.strokeCap,
                progress = progress,
            )
        } ?: run {
            CircularProgressIndicator(
                modifier = modifier.android,
                color = color,
                strokeWidth = configuration.android.strokeWidth,
                trackColor = configuration.android.trackColor,
                strokeCap = configuration.android.strokeCap,
            )
        }

        Platform.IOS -> CupertinoSpinner(
            modifier = modifier.iOS,
            color = color,
            text = configuration.iOS.text,
        )

        Platform.MacOS -> CupertinoSpinner(
            modifier = modifier.macOS,
            color = color,
            text = configuration.macOS.text,
        )

        Platform.Windows -> WindowsProgressRing(
            modifier = modifier.windows,
            progress = configuration.windows.progress,
            color = color,
            trackColor = configuration.windows.trackColor,
            strokeWidth = configuration.windows.strokeWidth,
        )
    }
}

object AdaptCircularIndicatorDefaults {
    @Composable
    fun configuration(
        android: AndroidCircularIndicatorConfiguration = AndroidCircularIndicatorConfiguration(
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
            trackColor = ProgressIndicatorDefaults.circularTrackColor,
            strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
            progress = null,
        ),
        iOS: IOSCircularIndicatorConfiguration = IOSCircularIndicatorConfiguration(
            text = null,
        ),
        macOS: MacOSCircularIndicatorConfiguration = MacOSCircularIndicatorConfiguration(
            text = null,
        ),
        windows: WindowsCircularIndicatorConfiguration = WindowsCircularIndicatorConfiguration(
            progress = null,
            trackColor = androidx.compose.ui.graphics.Color.Transparent,
            strokeWidth = WindowsProgressRingDefaults.StrokeWidthMedium,
        ),
    ) = AdaptCircularIndicatorConfiguration(
        android = android,
        iOS = iOS,
        macOS = macOS,
        windows = windows,
    )

    val Color @Composable get() = when(LocalPlatform.current) {
        Platform.Android, Platform.Web -> ProgressIndicatorDefaults.circularColor
        Platform.IOS -> CupertinoSpinnerDefaults.Color
        Platform.MacOS -> CupertinoSpinnerDefaults.Color
        Platform.Windows -> WindowsProgressRingDefaults.Color
    }
}

@Immutable
class AdaptCircularIndicatorConfiguration(
    val android: AndroidCircularIndicatorConfiguration,
    val iOS: IOSCircularIndicatorConfiguration,
    val macOS: MacOSCircularIndicatorConfiguration,
    val windows: WindowsCircularIndicatorConfiguration,
)

@Immutable
class AndroidCircularIndicatorConfiguration(
    val strokeWidth: Dp,
    val trackColor: Color,
    val strokeCap: StrokeCap,
    val progress: (() -> Float)?,
)

@Immutable
class IOSCircularIndicatorConfiguration(
    val text: (@Composable () -> Unit)?,
)

@Immutable
class MacOSCircularIndicatorConfiguration(
    val text: (@Composable () -> Unit)?,
)

@Immutable
class WindowsCircularIndicatorConfiguration(
    val progress: (() -> Float)?,
    val trackColor: Color,
    val strokeWidth: Dp,
)

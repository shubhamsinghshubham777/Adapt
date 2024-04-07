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

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import design.adapt.cupertino.CupertinoColorScheme
import design.adapt.cupertino.CupertinoTypography
import design.adapt.cupertino.IOSTheme
import design.adapt.cupertino.MacOSTheme
import design.adapt.cupertino.darkColorSchemeCupertino
import design.adapt.cupertino.lightColorSchemeCupertino
import design.adapt.windows.WindowsColorScheme
import design.adapt.windows.WindowsTheme
import design.adapt.windows.WindowsTypography
import design.adapt.windows.darkColorSchemeWindows
import design.adapt.windows.lightColorSchemeWindows

@Composable
fun AdaptTheme(
    configuration: AdaptThemeConfiguration = lightAdaptThemeConfiguration(),
    platform: Platform = design.adapt.platform,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPlatform provides platform,
        content = {
            MaterialTheme(
                colorScheme = configuration.android.colorScheme,
                typography = configuration.android.typography,
            ) {
                IOSTheme(
                    colorScheme = configuration.iOS.colorScheme,
                    typography = configuration.iOS.typography,
                ) {
                    MacOSTheme(
                        colorScheme = configuration.macOS.colorScheme,
                        typography = configuration.macOS.typography,
                    ) {
                        WindowsTheme(
                            colorScheme = configuration.windows.colorScheme,
                            typography = configuration.windows.typography,
                            content = content
                        )
                    }
                }
            }
        },
    )
}

@Immutable
data class AdaptThemeConfiguration(
    val android: AndroidThemeConfiguration,
    val iOS: IOSThemeConfiguration,
    val macOS: MacOSThemeConfiguration,
    val windows: WindowsThemeConfiguration,
)

@Immutable
data class AndroidThemeConfiguration(
    val colorScheme: ColorScheme,
    val shapes: Shapes,
    val typography: Typography,
)

@Immutable
data class IOSThemeConfiguration(
    val colorScheme: CupertinoColorScheme,
    val typography: CupertinoTypography,
)

@Immutable
data class MacOSThemeConfiguration(
    val colorScheme: CupertinoColorScheme,
    val typography: CupertinoTypography,
)

@Immutable
data class WindowsThemeConfiguration(
    val colorScheme: WindowsColorScheme,
    val typography: WindowsTypography,
)

fun lightAdaptThemeConfiguration(
    android: AndroidThemeConfiguration = AndroidThemeConfiguration(
        colorScheme = lightColorScheme(),
        shapes = Shapes(),
        typography = Typography(),
    ),
    iOS: IOSThemeConfiguration = IOSThemeConfiguration(
        colorScheme = lightColorSchemeCupertino(),
        typography = CupertinoTypography(),
    ),
    macOS: MacOSThemeConfiguration = MacOSThemeConfiguration(
        colorScheme = lightColorSchemeCupertino(),
        typography = CupertinoTypography(),
    ),
    windows: WindowsThemeConfiguration = WindowsThemeConfiguration(
        colorScheme = lightColorSchemeWindows(),
        typography = WindowsTypography(),
    ),
) = AdaptThemeConfiguration(
    android = android,
    iOS = iOS,
    macOS = macOS,
    windows = windows,
)

fun darkAdaptThemeConfiguration(
    android: AndroidThemeConfiguration = AndroidThemeConfiguration(
        colorScheme = darkColorScheme(),
        shapes = Shapes(),
        typography = Typography(),
    ),
    iOS: IOSThemeConfiguration = IOSThemeConfiguration(
        colorScheme = darkColorSchemeCupertino(),
        typography = CupertinoTypography(),
    ),
    macOS: MacOSThemeConfiguration = MacOSThemeConfiguration(
        colorScheme = darkColorSchemeCupertino(),
        typography = CupertinoTypography(),
    ),
    windows: WindowsThemeConfiguration = WindowsThemeConfiguration(
        colorScheme = darkColorSchemeWindows(),
        typography = WindowsTypography(),
    ),
) = AdaptThemeConfiguration(
    android = android,
    iOS = iOS,
    macOS = macOS,
    windows = windows,
)

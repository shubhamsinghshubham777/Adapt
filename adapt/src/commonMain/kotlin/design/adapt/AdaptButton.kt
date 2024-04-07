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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import design.adapt.cupertino.IOSButton
import design.adapt.cupertino.IOSButtonColors
import design.adapt.cupertino.IOSButtonDefaults
import design.adapt.cupertino.IOSButtonSize
import design.adapt.cupertino.IOSButtonStyle
import design.adapt.cupertino.MacOSButton
import design.adapt.cupertino.MacOSButtonColors
import design.adapt.cupertino.MacOSButtonDefaults
import design.adapt.cupertino.MacOSButtonStyle
import design.adapt.windows.WindowsButton
import design.adapt.windows.WindowsButtonColors
import design.adapt.windows.WindowsButtonDefaults
import design.adapt.windows.WindowsButtonIconSide
import design.adapt.windows.WindowsButtonSize
import design.adapt.windows.WindowsButtonStyle

@Composable
fun AdaptButton(
    onClick: () -> Unit,
    modifier: AdaptModifier = AdaptModifier(),
    enabled: Boolean = true,
    text: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    configuration: AdaptButtonConfiguration = AdaptButtonDefaults.configuration(
        hasIcon = icon != null,
        hasText = text != null,
    ),
) {
    when (LocalPlatform.current) {
        // TODO: Separate Web from Android here
        Platform.Android, Platform.Web -> {
            Button(
                onClick = onClick,
                modifier = modifier.android,
                enabled = enabled,
                shape = configuration.android.shape,
                colors = configuration.android.colors,
                contentPadding = when {
                    icon != null && text != null -> ButtonDefaults.ButtonWithIconContentPadding
                    else -> ButtonDefaults.ContentPadding
                },
                interactionSource = interactionSource,
                content = {
                    CompositionLocalProvider(
                        LocalContentColor provides configuration.android.colors.contentColor,
                    ) {
                        Row {
                            icon?.invoke()
                            Spacer(modifier = Modifier.width(8.dp))
                            text?.invoke()
                        }
                    }
                },
            )
        }

        Platform.IOS -> {
            IOSButton(
                onClick = onClick,
                modifier = modifier.iOS,
                size = configuration.iOS.size,
                style = configuration.iOS.style,
                colors = configuration.iOS.colors,
                icon = icon,
                text = text,
                shape = configuration.iOS.shape,
                enabled = enabled,
                onMaterial = configuration.iOS.onMaterial,
                interactionSource = interactionSource,
            )
        }

        Platform.MacOS -> {
            MacOSButton(
                onClick = onClick,
                style = configuration.macOS.style,
                modifier = modifier.iOS,
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        icon?.invoke()
                        Spacer(Modifier.width(4.dp))
                        text?.invoke()
                    }
                },
                colors = configuration.macOS.colors,
                shape = configuration.macOS.shape,
                enabled = enabled,
                interactionSource = interactionSource,
            )
        }

        Platform.Windows -> {
            WindowsButton(
                onClick = onClick,
                modifier = modifier.windows,
                style = configuration.windows.style,
                size = configuration.windows.size,
                icon = icon,
                text = text,
                iconSide = configuration.windows.iconSide,
                contentPadding = configuration.windows.contentPadding,
                colors = configuration.windows.colors,
                enabled = enabled,
                interactionSource = interactionSource,
                shape = configuration.windows.shape,
            )
        }
    }
}

@Immutable
data class AdaptButtonConfiguration(
    val android: AndroidButtonConfiguration,
    val iOS: IOSButtonConfiguration,
    val macOS: MacOSButtonConfiguration,
    val windows: WindowsButtonConfiguration,
)

@Immutable
data class AndroidButtonConfiguration(
    val colors: ButtonColors,
    val shape: Shape,
)

@Immutable
data class IOSButtonConfiguration(
    val colors: IOSButtonColors,
    val onMaterial: Boolean,
    val shape: Shape,
    val size: IOSButtonSize,
    val style: IOSButtonStyle,
)

@Immutable
data class MacOSButtonConfiguration(
    val colors: MacOSButtonColors,
    val shape: Shape,
    val style: MacOSButtonStyle,
)

@Immutable
data class WindowsButtonConfiguration(
    val colors: WindowsButtonColors,
    val contentPadding: PaddingValues,
    val iconSide: WindowsButtonIconSide,
    val shape: Shape,
    val size: WindowsButtonSize,
    val style: WindowsButtonStyle,
)

object AdaptButtonDefaults {
    @Composable
    fun configuration(
        hasIcon: Boolean,
        hasText: Boolean,
        android: AndroidButtonConfiguration = AndroidButtonConfiguration(
            colors = ButtonDefaults.buttonColors(),
            shape = ButtonDefaults.shape,
        ),
        iOS: IOSButtonConfiguration = IOSButtonConfiguration(
            colors = IOSButtonDefaults.colors(IOSButtonDefaults.style),
            onMaterial = false,
            shape = IOSButtonDefaults.shape(
                isIconOnly = hasIcon && !hasText,
                size = IOSButtonDefaults.size,
            ),
            size = IOSButtonDefaults.size,
            style = IOSButtonDefaults.style,
        ),
        macOS: MacOSButtonConfiguration = MacOSButtonConfiguration(
            colors = MacOSButtonDefaults.colors(MacOSButtonDefaults.style),
            shape = MacOSButtonDefaults.shape,
            style = MacOSButtonDefaults.style,
        ),
        windows: WindowsButtonConfiguration = WindowsButtonConfiguration(
            colors = WindowsButtonDefaults.colors(WindowsButtonDefaults.style),
            contentPadding = WindowsButtonDefaults.contentPadding(
                hasIcon = hasIcon,
                hasText = hasText,
                size = WindowsButtonDefaults.size,
                style = WindowsButtonDefaults.style,
            ),
            iconSide = WindowsButtonIconSide.Start,
            shape = WindowsButtonDefaults.shape,
            size = WindowsButtonDefaults.size,
            style = WindowsButtonDefaults.style,
        ),
    ) = AdaptButtonConfiguration(
        android = android,
        iOS = iOS,
        macOS = macOS,
        windows = windows,
    )
}

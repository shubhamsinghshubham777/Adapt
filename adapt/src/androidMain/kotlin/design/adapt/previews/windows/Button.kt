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

package design.adapt.previews.windows

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.R
import design.adapt.windows.WindowsButton
import design.adapt.windows.WindowsButtonIconSide
import design.adapt.windows.WindowsButtonSize
import design.adapt.windows.WindowsButtonStyle
import design.adapt.windows.WindowsIcon
import design.adapt.windows.WindowsText
import design.adapt.windows.WindowsTheme
import design.adapt.windows.darkWindowsColorScheme
import design.adapt.windows.lightWindowsColorScheme

@Preview(device = "spec:width=1080px,height=5000px,dpi=440")
@Composable
private fun LightButtons() {
    WindowsTheme(
        colorScheme = lightWindowsColorScheme(),
        content = {
            Box(
                modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFAFAFA))
            ) {
                WindowsButtonPreviews()
            }
        },
    )
}

@Preview(device = "spec:width=1080px,height=5000px,dpi=440")
@Composable
private fun DarkButtons() {
    WindowsTheme(
        colorScheme = darkWindowsColorScheme(),
        content = {
            Box(
                modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF1C1C1C))
            ) {
                WindowsButtonPreviews()
            }
        },
    )
}

@Composable
private fun WindowsButtonPreviews() {
    val text = remember {
        movableContentOf { WindowsText(text = "Text") }
    }

    val icon = remember {
        movableContentOf {
            WindowsIcon(
                modifier = Modifier.size(21.dp),
                painter = painterResource(id = R.drawable.ic_checkbox_circle),
                contentDescription = null,
            )
        }
    }

    val hoveredInteractionSource = remember {
        MutableInteractionSource().apply {
            tryEmit(HoverInteraction.Enter())
        }
    }

    val commonColumn: @Composable (@Composable ColumnScope.() -> Unit) -> Unit = remember {
        movableContentOf { content ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(72.dp),
                content = content,
            )
        }
    }

    Row {
        // Rest Buttons
        commonColumn {
            WindowsButton(onClick = {}, text = text, style = WindowsButtonStyle.Accent)
            WindowsButton(onClick = {}, text = text, style = WindowsButtonStyle.Standard)
            WindowsButton(onClick = {}, text = text, style = WindowsButtonStyle.Subtle)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Accent, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Accent, iconSide = WindowsButtonIconSide.End)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Standard, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Standard, iconSide = WindowsButtonIconSide.End)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Subtle, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Subtle, iconSide = WindowsButtonIconSide.End)
            WindowsButton(onClick = {}, icon = icon, style = WindowsButtonStyle.Standard)
            WindowsButton(onClick = {}, icon = icon, style = WindowsButtonStyle.Subtle)
            WindowsButton(onClick = {}, text = text, style = WindowsButtonStyle.Standard, size = WindowsButtonSize.Compact)
        }

        // Disabled Buttons
        commonColumn {
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, style = WindowsButtonStyle.Accent)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, style = WindowsButtonStyle.Standard)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, style = WindowsButtonStyle.Subtle)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Accent, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Accent, iconSide = WindowsButtonIconSide.End)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Standard, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Standard, iconSide = WindowsButtonIconSide.End)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Subtle, iconSide = WindowsButtonIconSide.Start)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, icon = icon, style = WindowsButtonStyle.Subtle, iconSide = WindowsButtonIconSide.End)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, icon = icon, style = WindowsButtonStyle.Standard)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, icon = icon, style = WindowsButtonStyle.Subtle)
            WindowsButton(enabled = false, interactionSource = hoveredInteractionSource, onClick = {}, text = text, style = WindowsButtonStyle.Standard, size = WindowsButtonSize.Compact)
        }
    }
}

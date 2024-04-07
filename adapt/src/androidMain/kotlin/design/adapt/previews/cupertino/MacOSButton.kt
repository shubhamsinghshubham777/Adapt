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

package design.adapt.previews.cupertino

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptText
import design.adapt.cupertino.MacOSButton
import design.adapt.cupertino.MacOSButtonStyle
import design.adapt.cupertino.MacOSTheme
import design.adapt.cupertino.darkColorSchemeCupertino
import design.adapt.cupertino.lightColorSchemeCupertino

@Preview
@Composable
private fun DarkMode() {
    MacOSTheme(colorScheme = darkColorSchemeCupertino()) {
        Buttons()
    }
}

@Preview
@Composable
private fun LightMode() {
    MacOSTheme(colorScheme = lightColorSchemeCupertino()) {
        Buttons()
    }
}

@Composable
private fun Buttons() {
    val text = remember {
        movableContentOf {
            AdaptText(text = "Label")
        }
    }

    Box(
        modifier = Modifier.background(Color(0xFFCCCCCC))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            MacOSButton(onClick = {}, text = text, style = MacOSButtonStyle.Primary, enabled = true)
            MacOSButton(onClick = {}, text = text, style = MacOSButtonStyle.Primary, enabled = false)
            MacOSButton(onClick = {}, text = text, style = MacOSButtonStyle.Secondary, enabled = true)
            MacOSButton(onClick = {}, text = text, style = MacOSButtonStyle.Secondary, enabled = false)
            MacOSButton(onClick = {}, text = text, style = MacOSButtonStyle.Destructive)
        }
    }
}

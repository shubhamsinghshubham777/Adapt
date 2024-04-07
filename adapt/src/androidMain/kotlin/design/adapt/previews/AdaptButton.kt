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

package design.adapt.previews

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptButton
import design.adapt.AdaptIcon
import design.adapt.AdaptText
import design.adapt.Platform
import design.adapt.R

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AndroidLightPreview() {
    Buttons(platform = Platform.Android)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AndroidDarkPreview() {
    Buttons(platform = Platform.Android)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun IOSLightPreview() {
    Buttons(platform = Platform.IOS)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IOSDarkPreview() {
    Buttons(platform = Platform.IOS)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MacOSLightPreview() {
    Buttons(platform = Platform.MacOS)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MacOSDarkPreview() {
    Buttons(platform = Platform.MacOS)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun WindowsLightPreview() {
    Buttons(platform = Platform.Windows)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WindowsDarkPreview() {
    Buttons(platform = Platform.Windows)
}

@Composable
private fun Buttons(platform: Platform) {
    val text = remember {
        movableContentOf {
            AdaptText(text = "Label")
        }
    }

    val icon = remember {
        movableContentOf {
            AdaptIcon(
                painter = painterResource(id = R.drawable.ic_checkbox_circle),
                contentDescription = null
            )
        }
    }

    AdaptPreviewsTheme(platform = platform) {
        Column(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.6f))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AdaptButton(onClick = {}, text = text, icon = icon, enabled = true)
            AdaptButton(onClick = {}, text = text, icon = icon, enabled = false)
        }
    }
}

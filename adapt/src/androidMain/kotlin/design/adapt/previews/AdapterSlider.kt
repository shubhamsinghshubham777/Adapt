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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptSlider
import design.adapt.Platform

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AndroidLightPreview() = Sliders(platform = Platform.Android)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AndroidDarkPreview() = Sliders(platform = Platform.Android)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun IOSLightPreview() = Sliders(platform = Platform.IOS)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IOSDarkPreview() = Sliders(platform = Platform.IOS)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MacOSLightPreview() = Sliders(platform = Platform.MacOS)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MacOSDarkPreview() = Sliders(platform = Platform.MacOS)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun WindowsLightPreview() = Sliders(platform = Platform.Windows)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WindowsDarkPreview() = Sliders(platform = Platform.Windows)

@Composable
private fun Sliders(platform: Platform) {
    AdaptPreviewsTheme(platform = platform) {
        Column(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.6f))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AdaptSlider(value = 0.3f, onValueChange = {})
            AdaptSlider(value = 0.3f, onValueChange = {}, enabled = false)
        }
    }
}

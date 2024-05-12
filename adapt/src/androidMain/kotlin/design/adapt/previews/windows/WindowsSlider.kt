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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import design.adapt.AdaptText
import design.adapt.windows.WindowsSlider
import design.adapt.windows.WindowsTheme

@Preview
@Composable
private fun WindowsSliderPreviews() {
    var value by remember { mutableFloatStateOf(0.3f) }
    WindowsTheme {
        WindowsSlider(
            value = value,
            onValueChange = { value = it },
            bufferProgress = 0.6f,
            header = { AdaptText(text = "Header") }
        )
    }
}

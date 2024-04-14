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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.windows.WindowsProgressRing
import design.adapt.windows.WindowsProgressRingDefaults
import design.adapt.windows.WindowsTheme

@Preview
@Composable
private fun ProgressRingPreview(
    modifier: Modifier = Modifier,
) {
    WindowsTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WindowsProgressRing(trackColor = WindowsProgressRingDefaults.TrackColor)
            WindowsProgressRing(progress = { 0.6f }, trackColor = WindowsProgressRingDefaults.TrackColor)
        }
    }
}

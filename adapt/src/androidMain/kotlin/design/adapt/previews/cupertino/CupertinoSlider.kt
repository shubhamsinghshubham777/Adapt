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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeMute
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptIcon
import design.adapt.cupertino.CupertinoSlider
import design.adapt.cupertino.MacOSTheme

@Preview
@Composable
private fun CupertinoSliderPreview() {
    var value by remember { mutableFloatStateOf(0.1f) }
    MacOSTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            CupertinoSlider(
                value = value,
                onValueChange = { value = it },
                steps = 4,
                leadingIcon = {
                    AdaptIcon(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .alpha(0.7f),
                        imageVector = Icons.AutoMirrored.Filled.VolumeMute,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    AdaptIcon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .alpha(0.7f),
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = null
                    )
                },
            )
            CupertinoSlider(
                value = value,
                onValueChange = { value = it },
            )
        }
    }
}

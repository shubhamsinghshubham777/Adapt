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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import design.adapt.AdaptPreviewsTheme
import design.adapt.WindowsButton
import design.adapt.WindowsButtonDefaults
import design.adapt.WindowsButtonType

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Light"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Dark"
)
@Composable
internal fun Button() {
    AdaptPreviewsTheme {
        Surface {
            WindowsButton(
                onClick = {},
            ) {
                Text(text = "Text")
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Light"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    name = "Dark"
)
@Composable
internal fun IconButton() {
    AdaptPreviewsTheme {
        Surface {
            WindowsButton(
                type = WindowsButtonType.Standard,
                contentPadding = WindowsButtonDefaults.ButtonWithIconOnlyContentPadding,
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        }
    }
}

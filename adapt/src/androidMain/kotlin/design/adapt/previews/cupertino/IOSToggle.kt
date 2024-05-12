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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.cupertino.IOSTheme
import design.adapt.cupertino.IOSToggle

@Preview
@Composable
private fun IOSTogglePreview() {
    IOSTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            IOSToggle(checked = false, onCheckedChange = {})
            IOSToggle(checked = true, onCheckedChange = {})
        }
    }
}
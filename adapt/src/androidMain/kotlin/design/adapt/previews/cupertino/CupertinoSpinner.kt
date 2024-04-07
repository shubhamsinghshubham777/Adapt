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
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptText
import design.adapt.cupertino.CupertinoSpinner
import design.adapt.cupertino.CupertinoSpinnerDefaults

@Preview
@Composable
private fun SpinnerPreview() {
    val text = remember {
        movableContentOf {
            AdaptText(text = "Status...")
        }
    }

    Box(
        modifier = Modifier
            .background(Color(0xFFCCCCCC))
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            CupertinoSpinner(
                modifier = Modifier.size(CupertinoSpinnerDefaults.sizeRegular),
                color = spinnerColor
            )
            CupertinoSpinner(
                modifier = Modifier.size(CupertinoSpinnerDefaults.sizeSmall),
                color = spinnerColor
            )
            CupertinoSpinner(
                modifier = Modifier.size(CupertinoSpinnerDefaults.sizeRegular),
                color = spinnerColor,
                text = text,
            )
            CupertinoSpinner(
                modifier = Modifier.size(CupertinoSpinnerDefaults.sizeSmall),
                color = spinnerColor,
                text = text,
            )
        }
    }
}

private val spinnerColor = Color(0x993c3c43)

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

package design.adapt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import design.adapt.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun App() = AppTheme {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var isFirstLoading by remember { mutableStateOf(false) }
            var isSecondLoading by remember { mutableStateOf(false) }
            val coroutineScope = rememberCoroutineScope()
            WindowsButton(
                onClick = {
                    coroutineScope.launch {
                        isFirstLoading = true
                        delay(3000)
                        isFirstLoading = false
                    }
                },
                requestFocus = true,
            ) { Text(if (isFirstLoading) "Loading..." else "Windows Button #1") }
            WindowsButton(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    coroutineScope.launch {
                        isSecondLoading = true
                        delay(3000)
                        isSecondLoading = false
                    }
                }
            ) { Text(if (isSecondLoading) "Loading..." else "Windows Button #2") }
        }
    }
}

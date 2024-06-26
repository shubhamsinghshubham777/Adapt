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

package design.adapt.windows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import design.adapt.LocalTextStyle

object WindowsTheme {
    val colorScheme: WindowsColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalWindowsColorScheme.current

    val typography: WindowsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWindowsTypography.current
}

@Composable
fun WindowsTheme(
    colorScheme: WindowsColorScheme = WindowsTheme.colorScheme,
    typography: WindowsTypography = WindowsTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWindowsColorScheme provides colorScheme,
        LocalWindowsTypography provides typography,
        LocalTextStyle provides typography.body,
        content = content
    )
}

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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.adapt.cupertino.CupertinoSpinner
import design.adapt.cupertino.IOSButton
import design.adapt.cupertino.MacOSButton
import design.adapt.windows.WindowsButton
import design.adapt.windows.WindowsProgressRing
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun App() {
    val coroutineScope = rememberCoroutineScope()
    val horizontalPagerState = rememberPagerState { 2 }

    AdaptTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .systemBarsPadding()
                .fillMaxSize()
        ) {
            Column {
                AdaptText(
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    text = "Adapt Demo",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                horizontalPagerState.animateScrollToPage(
                                    page = horizontalPagerState.currentPage - 1,
                                )
                            }
                        },
                        content = {
                            AdaptIcon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = null
                            )
                        }
                    )
                    AdaptText(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .weight(1f),
                        text = "Tap or Swipe horizontally to see more pages",
                        style = TextStyle(textAlign = TextAlign.Center),
                    )
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                horizontalPagerState.animateScrollToPage(
                                    page = horizontalPagerState.currentPage + 1,
                                )
                            }
                        },
                        content = {
                            AdaptIcon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                                contentDescription = null
                            )
                        }
                    )
                }
                HorizontalPager(state = horizontalPagerState) { page ->
                    when (page) {
                        0 -> ButtonsDemoPage()
                        else -> IndicatorsDemoPage()
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScaffold(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        AdaptText(
            modifier = Modifier.padding(top = 16.dp),
            text = title,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        content()
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun ButtonsDemoPage() {
    ColumnScaffold(title = "Buttons") {
        AdaptText(text = "This 👇🏼 button should look native-like on all platforms")
        AdaptButton(
            onClick = {},
            text = { AdaptText(text = "Adapt Button") }
        )
        AdaptText(
            text = "This 👇🏼 button should use the Material design system on all platforms"
        )
        Button(
            onClick = {},
            content = { Text(text = "Android Button") }
        )
        AdaptText(
            text = "This 👇🏼 button should use iOS' variant of the Cupertino design " +
                    "system on all platforms",
        )
        IOSButton(
            onClick = {},
            text = { AdaptText(text = "iOS Button") }
        )
        AdaptText(
            text = "This 👇🏼 button should use macOS' variant of the Cupertino design " +
                    "system on all platforms",
        )
        MacOSButton(
            onClick = {},
            text = { AdaptText(text = "macOS Button") }
        )
        AdaptText(
            text = "This 👇🏼 button should use the WinUI design system on all platforms",
        )
        WindowsButton(
            onClick = {},
            text = { AdaptText(text = "Windows Button") }
        )
    }
}

@Composable
fun IndicatorsDemoPage() {
    ColumnScaffold(title = "Indicators") {
        // TODO(shubham): Add AdaptCircularIndicator as well
        AdaptText(
            text = "This 👇🏼 indicator should use the Material design system on all platforms"
        )
        CircularProgressIndicator()
        AdaptText(
            text = "This 👇🏼 indicator should use iOS' variant of the Cupertino design " +
                    "system on all platforms",
        )
        CupertinoSpinner()
        AdaptText(
            text = "This 👇🏼 indicator should use the WinUI design system on all platforms",
        )
        WindowsProgressRing()
    }
}

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

package design.adapt.previews.ios

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import design.adapt.AdaptIcon
import design.adapt.AdaptText
import design.adapt.ios.IOSButton
import design.adapt.ios.IOSButtonDefaults
import design.adapt.ios.IOSButtonSize
import design.adapt.ios.IOSButtonStyle
import design.adapt.ios.IOSTheme
import design.adapt.ios.LocalIOSColorScheme
import design.adapt.ios.iOSColorSchemeDark

@Preview(device = "spec:id=reference_desktop,shape=Normal,width=1920,height=1700,unit=dp,dpi=160")
@Composable
fun LightMode() {
    Buttons()
}

@Preview(
    device = "spec:id=reference_desktop,shape=Normal,width=1920,height=1700,unit=dp,dpi=160",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DarkMode() {
    CompositionLocalProvider(LocalIOSColorScheme provides iOSColorSchemeDark) {
        Buttons()
    }
}

@Composable
private fun Buttons() {
    val text = remember {
        movableContentOf {
            AdaptText(text = "Play")
        }
    }
    val icon = remember {
        movableContentOf {
            AdaptIcon(
                modifier = Modifier.size(IOSButtonDefaults.IconSize),
                painter = rememberVectorPainter(image = Icons.Default.PlayArrow),
                contentDescription = null,
            )
        }
    }
    val padding1 = remember { movableContentOf { Spacer(modifier = Modifier.width(14.dp)) } }
    val padding2 = remember { movableContentOf { Spacer(modifier = Modifier.width(44.dp)) } }
    val padding3 = remember { movableContentOf { Spacer(modifier = Modifier.width(15.dp)) } }
    val padding4 = remember { movableContentOf { Spacer(modifier = Modifier.width(45.dp)) } }
    val padding5 = remember { movableContentOf { Spacer(modifier = Modifier.width(18.dp)) } }
    val padding6 = remember { movableContentOf { Spacer(modifier = Modifier.width(4.dp)) } }
    val padding7 = remember { movableContentOf { Spacer(modifier = Modifier.width(56.dp)) } }
    val padding8 = remember { movableContentOf { Spacer(modifier = Modifier.width(88.dp)) } }
    val padding9 = remember { movableContentOf { Spacer(modifier = Modifier.width(60.dp)) } }
    val padding10 = remember { movableContentOf { Spacer(modifier = Modifier.width(92.dp)) } }
    val padding11 = remember { movableContentOf { Spacer(modifier = Modifier.width(64.dp)) } }
    val padding12 = remember { movableContentOf { Spacer(modifier = Modifier.width(40.dp)) } }
    val padding13 = remember { movableContentOf { Spacer(modifier = Modifier.width(68.dp)) } }
    val gapBetweenButtonTypes = remember { movableContentOf { Spacer(modifier = Modifier.height(40.dp)) } }

    val commonRow: @Composable (@Composable RowScope.() -> Unit) -> Unit = remember {
        movableContentOf { content ->
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                content = content
            )
        }
    }

    IOSTheme {
        Box(
            modifier = Modifier
                .background(
                    color = Color(color = 0xFFCCCCCC),
                    shape = RoundedCornerShape(2.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.25f),
                    shape = RoundedCornerShape(2.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Borderless
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Plain, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Plain, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Bezeled Gray
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Gray, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Gray, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Bezeled
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Tinted, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Tinted, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Filled
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding1()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding2()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding3()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding4()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding5()
                    IOSButton(onClick = {}, text = text, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding7()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding8()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding9()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding10()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding11()
                    IOSButton(onClick = {}, icon = icon, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = true, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Small, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding12()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Medium, style = IOSButtonStyle.Filled, onMaterial = true)
                    padding13()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = false)
                    padding2()
                    IOSButton(onClick = {}, text = text, enabled = false, size = IOSButtonSize.Large, style = IOSButtonStyle.Filled, onMaterial = true)
                }
            }
        }
    }
}

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
import design.adapt.cupertino.CupertinoButton
import design.adapt.cupertino.CupertinoButtonDefaults
import design.adapt.cupertino.CupertinoButtonSize
import design.adapt.cupertino.CupertinoButtonStyle
import design.adapt.cupertino.CupertinoIcon
import design.adapt.cupertino.CupertinoText
import design.adapt.cupertino.CupertinoTheme
import design.adapt.cupertino.LocalCupertinoColorScheme
import design.adapt.cupertino.cupertinoColorSchemeDark

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
    CompositionLocalProvider(LocalCupertinoColorScheme provides cupertinoColorSchemeDark) {
        Buttons()
    }
}

@Composable
private fun Buttons() {
    val text = remember {
        movableContentOf {
            CupertinoText(text = "Play")
        }
    }
    val icon = remember {
        movableContentOf {
            CupertinoIcon(
                modifier = Modifier.size(CupertinoButtonDefaults.IconSize),
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

    CupertinoTheme {
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
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Borderless, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Bezeled Gray
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.BezeledGray, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Bezeled
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Bezeled, onMaterial = true)
                }

                gapBetweenButtonTypes()

                // Filled
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding1()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding3()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding4()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding5()
                    CupertinoButton(onClick = {}, text = text, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    padding6()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding7()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding8()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding9()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding10()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding11()
                    CupertinoButton(onClick = {}, icon = icon, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = true, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
                commonRow {
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Small, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding12()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Medium, style = CupertinoButtonStyle.Filled, onMaterial = true)
                    padding13()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = false)
                    padding2()
                    CupertinoButton(onClick = {}, text = text, enabled = false, size = CupertinoButtonSize.Large, style = CupertinoButtonStyle.Filled, onMaterial = true)
                }
            }
        }
    }
}

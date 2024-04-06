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

package design.adapt.cupertino

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalCupertinoColorScheme = compositionLocalOf { cupertinoColorSchemeLight }

@Immutable
data class CupertinoColorScheme(
    val systemRed: Color,
    val systemOrange: Color,
    val systemYellow: Color,
    val systemGreen: Color,
    val systemMint: Color,
    val systemTeal: Color,
    val systemCyan: Color,
    val systemBlue: Color,
    val systemIndigo: Color,
    val systemPurple: Color,
    val systemPink: Color,
    val systemBrown: Color,
    val systemBlack: Color,
    val systemGray: Color,
    val systemGray2: Color,
    val systemGray3: Color,
    val systemGray4: Color,
    val systemGray5: Color,
    val systemGray6: Color,
    val systemWhite: Color,
    val systemBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color,
    val systemBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color,
    val systemBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color,
    val labelPrimary: Color,
    val labelSecondary: Color,
    val labelTertiary: Color,
    val labelQuaternary: Color,
    val systemGroupedBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color,
    val systemGroupedBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color,
    val systemGroupedBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color,
    val fillPrimary: Color,
    val fillSecondary: Color,
    val fillTertiary: Color,
    val fillQuaternary: Color,
    val separatorOpaque: Color,
    val separatorNonOpaque: Color,
)

enum class CupertinoBackgroundColorVariant { Elevated, Base }

internal val cupertinoColorSchemeLight = CupertinoColorScheme(
    systemRed = Color(0XFFFF3B30),
    systemOrange = Color(0XFFFF9500),
    systemYellow = Color(0XFFFFCC00),
    systemGreen = Color(0XFF34C759),
    systemMint = Color(0XFF00C7BE),
    systemTeal = Color(0XFF30B0C7),
    systemCyan = Color(0XFF32ADE6),
    systemBlue = Color(0XFF007AFF),
    systemIndigo = Color(0XFF5856D6),
    systemPurple = Color(0XFFAF52DE),
    systemPink = Color(0XFFFF2D55),
    systemBrown = Color(0XFFA2845E),
    systemBlack = Color(0XFF000000),
    systemGray = Color(0XFF8E8E93),
    systemGray2 = Color(0XFFAEAEB2),
    systemGray3 = Color(0XFFC7C7CC),
    systemGray4 = Color(0XFFD1D1D6),
    systemGray5 = Color(0XFFE5E5EA),
    systemGray6 = Color(0XFFF2F2F7),
    systemWhite = Color(0XFFFFFFFF),
    systemBackgroundPrimary = { Color(0XFFFFFFFF) },
    systemBackgroundSecondary = { Color(0XFFF2F2F7) },
    systemBackgroundTertiary = { Color(0XFFFFFFFF) },
    labelPrimary = Color(0XFF000000),
    labelSecondary = Color(0X993C3C43),
    labelTertiary = Color(0X4D3C3C43),
    labelQuaternary = Color(0X2E3C3C43),
    systemGroupedBackgroundPrimary = { Color(0XFFF2F2F7) },
    systemGroupedBackgroundSecondary = { Color(0XFFFFFFFF) },
    systemGroupedBackgroundTertiary = { Color(0XFFF2F2F7) },
    fillPrimary = Color(0X33787880),
    fillSecondary = Color(0X29787880),
    fillTertiary = Color(0X1F767680),
    fillQuaternary = Color(0X14747480),
    separatorOpaque = Color(0XFFC6C6C8),
    separatorNonOpaque = Color(0X5C3C3C43),
)

internal val cupertinoColorSchemeDark = CupertinoColorScheme(
    systemRed = Color(0XFFFF453A),
    systemOrange = Color(0XFFFF9F0A),
    systemYellow = Color(0XFFFFD60A),
    systemGreen = Color(0XFF30D158),
    systemMint = Color(0XFF63E6E2),
    systemTeal = Color(0XFF40CBE0),
    systemCyan = Color(0XFF64D2FF),
    systemBlue = Color(0XFF0A84FF),
    systemIndigo = Color(0XFF5E5CE6),
    systemPurple = Color(0XFFBF5AF2),
    systemPink = Color(0XFFFF375F),
    systemBrown = Color(0XFFAC8E68),
    systemBlack = Color(0XFF000000),
    systemGray = Color(0XFF8E8E93),
    systemGray2 = Color(0XFF8E8E93),
    systemGray3 = Color(0XFF48484A),
    systemGray4 = Color(0XFF3A3A3C),
    systemGray5 = Color(0XFF2C2C2E),
    systemGray6 = Color(0XFF1C1C1E),
    systemWhite = Color(0XFFFFFFFF),
    systemBackgroundPrimary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF1C1C1E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF000000)
        }
    },
    systemBackgroundSecondary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF2C2C2E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF1C1C1E)
        }
    },
    systemBackgroundTertiary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF3A3A3C)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF2C2C2E)
        }
    },
    labelPrimary = Color(0XFFFFFFFF),
    labelSecondary = Color(0X99EBEBF5),
    labelTertiary = Color(0X4DEBEBF5),
    labelQuaternary = Color(0X29EBEBF5),
    systemGroupedBackgroundPrimary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF1C1C1E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF000000)
        }
    },
    systemGroupedBackgroundSecondary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF2C2C2E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF1C1C1E)
        }
    },
    systemGroupedBackgroundTertiary = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF3A3A3C)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF2C2C2E)
        }
    },
    fillPrimary = Color(0X5C787880),
    fillSecondary = Color(0X52787880),
    fillTertiary = Color(0X3D767680),
    fillQuaternary = Color(0X2E747480),
    separatorOpaque = Color(0XFF38383A),
    separatorNonOpaque = Color(0XA6545458),
)

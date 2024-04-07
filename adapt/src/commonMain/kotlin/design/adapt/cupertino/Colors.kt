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
import androidx.compose.ui.graphics.Color

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

fun lightColorSchemeCupertino(
    systemRed: Color = Color(0XFFFF3B30),
    systemOrange: Color = Color(0XFFFF9500),
    systemYellow: Color = Color(0XFFFFCC00),
    systemGreen: Color = Color(0XFF34C759),
    systemMint: Color = Color(0XFF00C7BE),
    systemTeal: Color = Color(0XFF30B0C7),
    systemCyan: Color = Color(0XFF32ADE6),
    systemBlue: Color = Color(0XFF007AFF),
    systemIndigo: Color = Color(0XFF5856D6),
    systemPurple: Color = Color(0XFFAF52DE),
    systemPink: Color = Color(0XFFFF2D55),
    systemBrown: Color = Color(0XFFA2845E),
    systemBlack: Color = Color(0XFF000000),
    systemGray: Color = Color(0XFF8E8E93),
    systemGray2: Color = Color(0XFFAEAEB2),
    systemGray3: Color = Color(0XFFC7C7CC),
    systemGray4: Color = Color(0XFFD1D1D6),
    systemGray5: Color = Color(0XFFE5E5EA),
    systemGray6: Color = Color(0XFFF2F2F7),
    systemWhite: Color = Color(0XFFFFFFFF),
    systemBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFFFFFFF) },
    systemBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFF2F2F7) },
    systemBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFFFFFFF) },
    labelPrimary: Color = Color(0XFF000000),
    labelSecondary: Color = Color(0X993C3C43),
    labelTertiary: Color = Color(0X4D3C3C43),
    labelQuaternary: Color = Color(0X2E3C3C43),
    systemGroupedBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFF2F2F7) },
    systemGroupedBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFFFFFFF) },
    systemGroupedBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color = { Color(0XFFF2F2F7) },
    fillPrimary: Color = Color(0X33787880),
    fillSecondary: Color = Color(0X29787880),
    fillTertiary: Color = Color(0X1F767680),
    fillQuaternary: Color = Color(0X14747480),
    separatorOpaque: Color = Color(0XFFC6C6C8),
    separatorNonOpaque: Color = Color(0X5C3C3C43),
) = CupertinoColorScheme(
    systemRed = systemRed,
    systemOrange = systemOrange,
    systemYellow = systemYellow,
    systemGreen = systemGreen,
    systemMint = systemMint,
    systemTeal = systemTeal,
    systemCyan = systemCyan,
    systemBlue = systemBlue,
    systemIndigo = systemIndigo,
    systemPurple = systemPurple,
    systemPink = systemPink,
    systemBrown = systemBrown,
    systemBlack = systemBlack,
    systemGray = systemGray,
    systemGray2 = systemGray2,
    systemGray3 = systemGray3,
    systemGray4 = systemGray4,
    systemGray5 = systemGray5,
    systemGray6 = systemGray6,
    systemWhite = systemWhite,
    systemBackgroundPrimary = systemBackgroundPrimary,
    systemBackgroundSecondary = systemBackgroundSecondary,
    systemBackgroundTertiary = systemBackgroundTertiary,
    labelPrimary = labelPrimary,
    labelSecondary = labelSecondary,
    labelTertiary = labelTertiary,
    labelQuaternary = labelQuaternary,
    systemGroupedBackgroundPrimary = systemGroupedBackgroundPrimary,
    systemGroupedBackgroundSecondary = systemGroupedBackgroundSecondary,
    systemGroupedBackgroundTertiary = systemGroupedBackgroundTertiary,
    fillPrimary = fillPrimary,
    fillSecondary = fillSecondary,
    fillTertiary = fillTertiary,
    fillQuaternary = fillQuaternary,
    separatorOpaque = separatorOpaque,
    separatorNonOpaque = separatorNonOpaque,
)

fun darkColorSchemeCupertino(
    systemRed: Color = Color(0XFFFF453A),
    systemOrange: Color = Color(0XFFFF9F0A),
    systemYellow: Color = Color(0XFFFFD60A),
    systemGreen: Color = Color(0XFF30D158),
    systemMint: Color = Color(0XFF63E6E2),
    systemTeal: Color = Color(0XFF40CBE0),
    systemCyan: Color = Color(0XFF64D2FF),
    systemBlue: Color = Color(0XFF0A84FF),
    systemIndigo: Color = Color(0XFF5E5CE6),
    systemPurple: Color = Color(0XFFBF5AF2),
    systemPink: Color = Color(0XFFFF375F),
    systemBrown: Color = Color(0XFFAC8E68),
    systemBlack: Color = Color(0XFF000000),
    systemGray: Color = Color(0XFF8E8E93),
    systemGray2: Color = Color(0XFF8E8E93),
    systemGray3: Color = Color(0XFF48484A),
    systemGray4: Color = Color(0XFF3A3A3C),
    systemGray5: Color = Color(0XFF2C2C2E),
    systemGray6: Color = Color(0XFF1C1C1E),
    systemWhite: Color = Color(0XFFFFFFFF),
    systemBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF1C1C1E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF000000)
        }
    },
    systemBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF2C2C2E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF1C1C1E)
        }
    },
    systemBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF3A3A3C)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF2C2C2E)
        }
    },
    labelPrimary: Color = Color(0XFFFFFFFF),
    labelSecondary: Color = Color(0X99EBEBF5),
    labelTertiary: Color = Color(0X4DEBEBF5),
    labelQuaternary: Color = Color(0X29EBEBF5),
    systemGroupedBackgroundPrimary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF1C1C1E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF000000)
        }
    },
    systemGroupedBackgroundSecondary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF2C2C2E)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF1C1C1E)
        }
    },
    systemGroupedBackgroundTertiary: (CupertinoBackgroundColorVariant) -> Color = { variant ->
        when (variant) {
            CupertinoBackgroundColorVariant.Elevated -> Color(0XFF3A3A3C)
            CupertinoBackgroundColorVariant.Base -> Color(0XFF2C2C2E)
        }
    },
    fillPrimary: Color = Color(0X5C787880),
    fillSecondary: Color = Color(0X52787880),
    fillTertiary: Color = Color(0X3D767680),
    fillQuaternary: Color = Color(0X2E747480),
    separatorOpaque: Color = Color(0XFF38383A),
    separatorNonOpaque: Color = Color(0XA6545458),
) = CupertinoColorScheme(
    systemRed = systemRed,
    systemOrange = systemOrange,
    systemYellow = systemYellow,
    systemGreen = systemGreen,
    systemMint = systemMint,
    systemTeal = systemTeal,
    systemCyan = systemCyan,
    systemBlue = systemBlue,
    systemIndigo = systemIndigo,
    systemPurple = systemPurple,
    systemPink = systemPink,
    systemBrown = systemBrown,
    systemBlack = systemBlack,
    systemGray = systemGray,
    systemGray2 = systemGray2,
    systemGray3 = systemGray3,
    systemGray4 = systemGray4,
    systemGray5 = systemGray5,
    systemGray6 = systemGray6,
    systemWhite = systemWhite,
    systemBackgroundPrimary = systemBackgroundPrimary,
    systemBackgroundSecondary = systemBackgroundSecondary,
    systemBackgroundTertiary = systemBackgroundTertiary,
    labelPrimary = labelPrimary,
    labelSecondary = labelSecondary,
    labelTertiary = labelTertiary,
    labelQuaternary = labelQuaternary,
    systemGroupedBackgroundPrimary = systemGroupedBackgroundPrimary,
    systemGroupedBackgroundSecondary = systemGroupedBackgroundSecondary,
    systemGroupedBackgroundTertiary = systemGroupedBackgroundTertiary,
    fillPrimary = fillPrimary,
    fillSecondary = fillSecondary,
    fillTertiary = fillTertiary,
    fillQuaternary = fillQuaternary,
    separatorOpaque = separatorOpaque,
    separatorNonOpaque = separatorNonOpaque,
)

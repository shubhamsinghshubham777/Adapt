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

package design.adapt.winui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalWindowsColorScheme = compositionLocalOf { lightWindowsColorScheme() }

@Immutable
data class WindowsColorScheme(
    val fillAccentDefault: Color,
    val fillAccentSecondary: Color,
    val fillAccentTertiary: Color,
    val fillAccentDisabled: Color,
    val fillAccentSelectedTextBackground: Color,
    val fillControlDefault: Color,
    val fillControlSecondary: Color,
    val fillControlTertiary: Color,
    val fillControlDisabled: Color,
    val fillSubtleTransparent: Color,
    val fillSubtleSecondary: Color,
    val fillSubtleTertiary: Color,
    val fillSubtleDisabled: Color,
    val fillTextPrimary: Color,
    val fillTextSecondary: Color,
    val fillTextDisabled: Color,
    val fillTextOnAccentPrimary: Color,
    val fillTextOnAccentSecondary: Color,
    val fillTextOnAccentDisabled: Color,
    val fillTextOnAccentSelectedText: Color,
    val elevationAccentControlBorder: List<Color>,
    val elevationControlBorder: List<Color>,
    val strokeControlDefault: Color,
    val focusStrokeOuter: Color,
    val focusStrokeInner: Color,
)

fun lightWindowsColorScheme(
    fillAccentDefault: Color = Color(0XFF005FB8),
    fillAccentSecondary: Color = Color(0XE6005FB8),
    fillAccentTertiary: Color = Color(0XCC005FB8),
    fillAccentDisabled: Color = Color(0X36000000),
    fillAccentSelectedTextBackground: Color = Color(0XFF0078D4),
    fillControlDefault: Color = Color(0xb3ffffff),
    fillControlSecondary: Color = Color(0x80f9f9f9),
    fillControlTertiary: Color = Color(0x4df9f9f9),
    fillControlDisabled: Color = Color(0x4df9f9f9),
    fillSubtleTransparent: Color = Color.Transparent,
    fillSubtleSecondary: Color = Color(0xa000000),
    fillSubtleTertiary: Color = Color(0x8000000),
    fillSubtleDisabled: Color = Color.Transparent,
    fillTextPrimary: Color = Color(0xe3000000),
    fillTextSecondary: Color = Color(0x99000000),
    fillTextDisabled: Color = Color(0x5c000000),
    fillTextOnAccentPrimary: Color = Color(0XFFFFFFFF),
    fillTextOnAccentSecondary: Color = Color(0XB3FFFFFF),
    fillTextOnAccentDisabled: Color = Color(0XFFFFFFFF),
    fillTextOnAccentSelectedText: Color = Color(0XFFFFFFFF),
    elevationAccentControlBorder: List<Color> = listOf(
        Color(0x14ffffff),
        Color(0x66000000),
    ),
    elevationControlBorder: List<Color> = listOf(
        Color(0xf000000),
        Color(0x29000000),
    ),
    strokeControlDefault: Color = Color(0xf000000),
    focusStrokeOuter: Color = Color(0xe3000000),
    focusStrokeInner: Color = Color.White,
) = WindowsColorScheme(
    fillAccentDefault = fillAccentDefault,
    fillAccentSecondary = fillAccentSecondary,
    fillAccentTertiary = fillAccentTertiary,
    fillAccentDisabled = fillAccentDisabled,
    fillAccentSelectedTextBackground = fillAccentSelectedTextBackground,
    fillControlDefault = fillControlDefault,
    fillControlSecondary = fillControlSecondary,
    fillControlTertiary = fillControlTertiary,
    fillControlDisabled = fillControlDisabled,
    fillSubtleTransparent = fillSubtleTransparent,
    fillSubtleSecondary = fillSubtleSecondary,
    fillSubtleTertiary = fillSubtleTertiary,
    fillSubtleDisabled = fillSubtleDisabled,
    fillTextPrimary = fillTextPrimary,
    fillTextSecondary = fillTextSecondary,
    fillTextDisabled = fillTextDisabled,
    fillTextOnAccentPrimary = fillTextOnAccentPrimary,
    fillTextOnAccentSecondary = fillTextOnAccentSecondary,
    fillTextOnAccentDisabled = fillTextOnAccentDisabled,
    fillTextOnAccentSelectedText = fillTextOnAccentSelectedText,
    elevationAccentControlBorder = elevationAccentControlBorder,
    elevationControlBorder = elevationControlBorder,
    strokeControlDefault = strokeControlDefault,
    focusStrokeOuter = focusStrokeOuter,
    focusStrokeInner = focusStrokeInner,
)

fun darkWindowsColorScheme(
    fillAccentDefault: Color = Color(0XFF60CDFF),
    fillAccentSecondary: Color = Color(0XE660CDFF),
    fillAccentTertiary: Color = Color(0XCC60CDFF),
    fillAccentDisabled: Color = Color(0X29FFFFFF),
    fillAccentSelectedTextBackground: Color = Color(0XFF0078D4),
    fillControlDefault: Color = Color(0xfffffff),
    fillControlSecondary: Color = Color(0x14ffffff),
    fillControlTertiary: Color = Color(0x8ffffff),
    fillControlDisabled: Color = Color(0xaffffff),
    fillSubtleTransparent: Color = Color.Transparent,
    fillSubtleSecondary: Color = Color(0xfffffff),
    fillSubtleTertiary: Color = Color(0xaffffff),
    fillSubtleDisabled: Color = Color.Transparent,
    fillTextPrimary: Color = Color.White,
    fillTextSecondary: Color = Color(0xc7ffffff),
    fillTextDisabled: Color = Color(0x5cffffff),
    fillTextOnAccentPrimary: Color = Color(0XFF000000),
    fillTextOnAccentSecondary: Color = Color(0X80000000),
    fillTextOnAccentDisabled: Color = Color(0X87FFFFFF),
    fillTextOnAccentSelectedText: Color = Color(0XFFFFFFFF),
    elevationAccentControlBorder: List<Color> = listOf(
        Color(0x14ffffff),
        Color(0x24000000),
    ),
    elevationControlBorder: List<Color> = listOf(
        Color(0x17ffffff),
        Color(0x12ffffff),
    ),
    strokeControlDefault: Color = Color(0x12ffffff),
    focusStrokeOuter: Color = Color.White,
    focusStrokeInner: Color = Color(0xb3000000),
) = WindowsColorScheme(
    fillAccentDefault = fillAccentDefault,
    fillAccentSecondary = fillAccentSecondary,
    fillAccentTertiary = fillAccentTertiary,
    fillAccentDisabled = fillAccentDisabled,
    fillAccentSelectedTextBackground = fillAccentSelectedTextBackground,
    fillControlDefault = fillControlDefault,
    fillControlSecondary = fillControlSecondary,
    fillControlTertiary = fillControlTertiary,
    fillControlDisabled = fillControlDisabled,
    fillSubtleTransparent = fillSubtleTransparent,
    fillSubtleSecondary = fillSubtleSecondary,
    fillSubtleTertiary = fillSubtleTertiary,
    fillSubtleDisabled = fillSubtleDisabled,
    fillTextPrimary = fillTextPrimary,
    fillTextSecondary = fillTextSecondary,
    fillTextDisabled = fillTextDisabled,
    fillTextOnAccentPrimary = fillTextOnAccentPrimary,
    fillTextOnAccentSecondary = fillTextOnAccentSecondary,
    fillTextOnAccentDisabled = fillTextOnAccentDisabled,
    fillTextOnAccentSelectedText = fillTextOnAccentSelectedText,
    elevationAccentControlBorder = elevationAccentControlBorder,
    elevationControlBorder = elevationControlBorder,
    strokeControlDefault = strokeControlDefault,
    focusStrokeOuter = focusStrokeOuter,
    focusStrokeInner = focusStrokeInner,
)

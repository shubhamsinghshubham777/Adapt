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

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * A button gives the user a way to trigger an immediate action. Some buttons are specialised for
 * particular tasks, such as navigation, repeated actions, or presenting menus.
 *
 * @param onClick Action to be performed when the button is tapped/clicked
 * @param modifier [Modifier] to be used for view customisation
 * @param enabled Disables button click, focusability, and reduces opacity to emphasise the
 * unavailability of this button to participate in user actions. Defaults to true.
 * @param dense Reduces the touch target size and content padding of the button. Defaults to false.
 * @param type Defines the style of the button. Possible values are: Accent, Standard, Subtle.
 * Defaults to [WindowsButtonType.Accent].
 * @param contentPadding Sets the space between the content composable and the button's container
 * edges. Defaults to [WindowsButtonDefaults.DenseContentPadding]
 * or [WindowsButtonDefaults.ContentPadding] based on [dense].
 * @param containerColor Sets [Color] for button background. Defaults to
 * [WindowsButtonDefaults.color].
 * @param contentColor Overrides [LocalContentColor] for the content composable (applied to
 * composables like Text, Icon, etc). It is generally an inverse for [containerColor]. Defaults to
 * [WindowsButtonDefaults.contentColor].
 * @param requestFocus Sets whether the button requests focus when initialised. Defaults to false.
 * Note: If multiple composables set this param to true, then the focus will end on the composable
 * that requested it last.
 * @param content Composable to be shown inside the bounds of the button
 */
@Composable
fun WindowsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dense: Boolean = false,
    type: WindowsButtonType = WindowsButtonType.Accent,
    contentPadding: PaddingValues = if (dense) WindowsButtonDefaults.DenseContentPadding else
        WindowsButtonDefaults.ContentPadding,
    containerColor: Color = WindowsButtonDefaults.color(type = type),
    contentColor: Color = WindowsButtonDefaults.contentColor(type = type),
    requestFocus: Boolean = false,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val opacity = WindowsButtonDefaults.animatedOpacity(
        interactionState = interactionSource.toWindowsInteractionState(enabled = enabled)
    )
    val borderGradient = WindowsButtonDefaults.BorderGradient
    val density = LocalDensity.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) { if (requestFocus) focusRequester.requestFocus() }

    Box(
        modifier = modifier
            .focusRequester(focusRequester)
            .handleKeyEvents(interactionSource = interactionSource, onClick = onClick)
            .graphicsLayer { alpha = opacity }
            .border(
                width = WindowsButtonDefaults.FocusBorderWidth,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (isFocused) 1f else 0f),
                shape = WindowsButtonDefaults.ButtonFocusBorderShape,
            )
            .padding(
                WindowsButtonDefaults.BorderWidth
                        // Always taking focus border width as we do not want the button's size to
                        // change onFocus and change its alignment unexpectedly
                        + WindowsButtonDefaults.FocusBorderWidth
            )
            .drawWithContent {
                val cornerRadius = CornerRadius(
                    x = WindowsButtonDefaults.ButtonShape.topStart.toPx(size, density),
                    y = WindowsButtonDefaults.ButtonShape.topStart.toPx(size, density),
                )
                drawRoundRect(color = containerColor, cornerRadius = cornerRadius)
                if (type != WindowsButtonType.Subtle) {
                    drawRoundRect(
                        brush = borderGradient,
                        cornerRadius = cornerRadius,
                        style = Stroke(width = WindowsButtonDefaults.BorderWidth.toPx())
                    )
                }
                drawContent()
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
                enabled = enabled,
            )
            .focusable(enabled = enabled, interactionSource = interactionSource)
            .semantics {
                if (!enabled) this.disabled()
                this.onClick(
                    action = {
                        onClick()
                        return@onClick true
                    }
                )
            }
            .padding(contentPadding),
        content = {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                content = content
            )
        }
    )
}

/**
 * Contains default values for [WindowsButton]'s properties.
 */
// TODO: Check access modifiers for all variables inside
object WindowsButtonDefaults {
    val ButtonShape = RoundedCornerShape(4.dp)
    val ButtonFocusBorderShape = RoundedCornerShape(8.dp)

    val ContentPadding = PaddingValues(
        start = 47.5.dp,
        end = 47.5.dp,
        top = 5.dp,
        bottom = 7.dp,
    )

    val DenseContentPadding = PaddingValues(
        start = 11.dp,
        end = 11.dp,
        top = 0.dp,
        bottom = 2.dp,
    )

    val ButtonWithIconOnlyContentPadding = PaddingValues(all = 12.dp)

    private val LightBorderGradientTopColor = Color.Black.copy(alpha = 0.0578f)
    private val LightBorderGradientBottomColor = Color.Black.copy(alpha = 0.1622f)
    private val DarkBorderGradientTopColor = Color.White.copy(alpha = 0.093f)
    private val DarkBorderGradientBottomColor = Color.White.copy(alpha = 0.0698f)

    internal val BorderWidth = 1.dp
    internal val FocusBorderWidth = 2.5.dp

    internal val BorderGradient: Brush
        @Composable
        @ReadOnlyComposable
        get() {
            val isDarkMode = isSystemInDarkTheme()
            return Brush.verticalGradient(
                colors = if (isDarkMode) listOf(
                    DarkBorderGradientTopColor,
                    DarkBorderGradientBottomColor
                ) else listOf(
                    LightBorderGradientTopColor,
                    LightBorderGradientBottomColor
                ),
            )
        }

    fun animatedOpacity(interactionState: State<WindowsInteractionState>): Float {
        return when (interactionState.value) {
            WindowsInteractionState.Rest -> 1f
            WindowsInteractionState.Hover -> 0.9f
            WindowsInteractionState.Pressed -> 0.8f
            WindowsInteractionState.Disabled -> 0.2169f
            WindowsInteractionState.Focus -> 1f
        }
    }

    @Composable
    internal fun color(type: WindowsButtonType): Color {
        return when (type) {
            WindowsButtonType.Accent -> MaterialTheme.colorScheme.primary
            WindowsButtonType.Standard -> MaterialTheme.colorScheme.surface
            WindowsButtonType.Subtle -> Color.Transparent
        }
    }

    @Composable
    internal fun contentColor(type: WindowsButtonType): Color {
        return when (type) {
            WindowsButtonType.Accent -> MaterialTheme.colorScheme.onPrimary
            WindowsButtonType.Standard -> MaterialTheme.colorScheme.onSurface
            WindowsButtonType.Subtle -> MaterialTheme.colorScheme.onSurface
        }
    }
}

@Immutable
enum class WindowsButtonType { Accent, Standard, Subtle }

@Immutable
enum class WindowsInteractionState { Rest, Hover, Pressed, Disabled, Focus }

@Composable
private fun InteractionSource.toWindowsInteractionState(
    enabled: Boolean,
): State<WindowsInteractionState> {
    val pressed by collectIsPressedAsState()
    val hovered by collectIsHoveredAsState()
    val focused by collectIsFocusedAsState()
    val state = remember { mutableStateOf(WindowsInteractionState.Rest) }

    LaunchedEffect(pressed, hovered, focused) {
        state.value = when {
            !enabled -> WindowsInteractionState.Disabled
            pressed -> WindowsInteractionState.Pressed
            hovered -> WindowsInteractionState.Hover
            focused -> WindowsInteractionState.Focus
            else -> WindowsInteractionState.Rest
        }
    }

    return state
}

private val AcceptedKeys = listOf(
    Key.Escape,
    Key.DirectionUp,
    Key.DirectionDown,
    Key.DirectionLeft,
    Key.DirectionRight,
    Key.Spacebar,
    Key.Enter,
    Key.NumPadEnter,
)

/**
 * Handles major key events to enable a composable's focus to interact well with devices that
 * primarily depend on hardware input (like desktop, laptop, TV, etc).
 */
@Composable
private fun Modifier.handleKeyEvents(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit,
) = composed {
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    var pressInteraction: PressInteraction.Press? by remember { mutableStateOf(null) }
    this.then(
        onPreviewKeyEvent { keyEvent ->
            when (keyEvent.key) {
                Key.Escape -> focusManager.clearFocus()
                Key.DirectionUp -> focusManager.moveFocus(FocusDirection.Up)
                Key.DirectionDown -> focusManager.moveFocus(FocusDirection.Down)
                Key.DirectionLeft -> focusManager.moveFocus(FocusDirection.Left)
                Key.DirectionRight -> focusManager.moveFocus(FocusDirection.Right)

                Key.Spacebar, Key.Enter, Key.NumPadEnter -> {
                    when (keyEvent.type) {
                        KeyEventType.KeyDown -> {
                            coroutineScope.launch {
                                if (pressInteraction == null) {
                                    pressInteraction = PressInteraction.Press(Offset.Zero)
                                    pressInteraction?.let { interactionSource.emit(it) }
                                }
                            }
                        }

                        KeyEventType.KeyUp -> {
                            coroutineScope.launch {
                                pressInteraction?.let {
                                    interactionSource.emit(PressInteraction.Release(it))
                                    pressInteraction = null
                                    onClick()
                                }
                            }
                        }

                        KeyEventType.Unknown -> {
                            coroutineScope.launch {
                                pressInteraction?.let {
                                    interactionSource.emit(PressInteraction.Cancel(it))
                                    pressInteraction = null
                                }
                            }
                        }
                    }
                }

                else -> Unit
            }
            return@onPreviewKeyEvent AcceptedKeys.contains(keyEvent.key)
        }
    )
}

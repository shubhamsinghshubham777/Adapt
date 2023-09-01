package design.adapt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import design.adapt.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Column {
        AdaptButton(modifier = Modifier.padding(32.dp)) {
            println("Welcome to Adapt!")
        }
    }
}

internal expect fun openUrl(url: String?)

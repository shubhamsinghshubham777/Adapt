import androidx.compose.ui.window.ComposeUIViewController
import design.adapt.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}

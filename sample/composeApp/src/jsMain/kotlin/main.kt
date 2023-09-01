import design.adapt.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Adapt") {
            App()
        }
    }
}

# Adapt
![badge][badge-android] ![badge][badge-ios] ![badge][badge-js] ![badge][badge-jvm]

Adapt is a Compose-based UI component library that provides you components from various commonly
used design systems on different platforms like Material 3 (for Android & Web), Cupertino (for
iOS & macOS), and WinUI 3 (for Windows).

Adapt provides a Flutter-like experience where you can mix-and-match UI components between platforms
to achieve your desired UI/UX output(s) (e.g. using Cupertino components on Android devices and 
Material components on Apple devices).

## Types of components
Adapt offers the following 2 types of composables/components:
1. **Platform-specific** - These look the same on all platforms (e.g. `WindowsButton`, `CupertinoSpinner`)
2. **Platform-agnostic** - These look native-like on every platform (e.g. `AdaptButton`, `AdaptCircularIndicator`).

## Platform-agnostic components
Along with Platform-specific components that allow you to write common UI for all platforms, Adapt also provides you 
with Platform-agnostic components that display native-like components on the current platform you're running.

For example, the following common code:
```kotlin
@Composable
internal fun App() {
    AdaptButton(
        modifier = AdaptModifier(common = Modifier.fillMaxSize().wrapContentSize()),
        onClick = {},
        text = { AdaptText("Click me") }
    )
}
```
would produce the following results on different platforms 👇

| Android (Material) | iOS (Cupertino) | Windows (WinUI3) |
|--------------------|-----------------|------------------|
| <img src="https://github.com/shubhamsinghshubham777/Adapt/assets/46640516/d43113fb-a0f8-4e05-8543-45e23008c79a" width=250></img> | <img src="https://github.com/shubhamsinghshubham777/Adapt/assets/46640516/c55879b8-5f65-493e-af84-c119de1f968a" width=250></img> | <img src="https://github.com/shubhamsinghshubham777/Adapt/assets/46640516/17cf6a74-d308-4269-99a9-597a483caa65" width=600></img> |

Hence, you can mix and match Platform-specific and agnostic components according to your desired output(s) 🌟

## Get Started 🚀
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.shubhamsinghshubham777/adapt?server=https%3A%2F%2Fs01.oss.sonatype.org&style=flat&label=Latest%20Version)

Add the following to your shared module's `build.gradle.kts`:
```kotlin
implementation("io.github.shubhamsinghshubham777:adapt:0.0.1-SNAPSHOT") // Get the latest version from GitHub Releases/Tags
```

### For SNAPSHOT versions
Add the following to your project level `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        ...
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/") // Add this
    }
}
```

Replace `Material` based components with `Adapt` based components:
### Old Code
```kotlin
@Composable
fun MyApp() {
    MaterialTheme {
        Button(onClick = {}, content = { Text("Click me") })
    }
}
```

### New Code
```kotlin
@Composable
fun MyApp() {
    AdaptTheme {
        AdaptButton(onClick = {}, text = { Text("Click me") })
        // You can also use platform-specific components like IOSButton, MacOSButton, or WindowsButton
    }
}
```

## Available Components ✅

| Name                | Image                                                     | Platform-agnostic variant |
|---------------------|-----------------------------------------------------------|---------------------------|
| CupertinoSlider     | ![CupertinoSlider](/assets/cupertino_sliders.png)         | AdaptSlider               |
| CupertinoSpinner    | ![CupertinoSpinner](/assets/cupertino_spinner.png)        | AdaptCircularIndicator    |
| IOSButton           | ![IOSButton](/assets/iOS_button.png)                      | AdaptButton               |
| IOSToggle           | ![IOSToggle](/assets/iOS_toggle.png)                      | AdaptSwitch               |
| MacOSButton         | ![MacOSButton](/assets/macOS_button.png)                  | AdaptButton               |
| MacOSSwitch         | ![MacOSSwitch](/assets/macOS_switch.png)                  | AdaptSwitch               |
| WindowsButton       | ![WindowsButton](/assets/windows_button.png)              | AdaptButton               |
| WindowsProgressRing | ![WindowsProgressRing](/assets/windows_progress_ring.png) | AdaptCircularIndicator    |
| WindowsSlider       | ![WindowsSlider](/assets/windows_slider.png)              | AdaptSlider               |
| WindowsToggleSwitch | ![WindowsToggleSwitch](/assets/windows_toggle_switch.png) | AdaptSwitch               |

## Planned Components 🚧
The long term goal of this library is to recreate all components from major design systems like
`Material`, `Cupertino`, `WinUI`, etc. to allow developers to seamlessly create multiplatform apps 
that look, feel, and behave native-like.

Expected upcoming components are: `Checkbox`, `TextField`, and `Date/Time Pickers`.
Expected platform support: WASM (JS)

## Run Demo/Sample

https://github.com/shubhamsinghshubham777/Adapt/assets/46640516/2cdbcc06-64f0-4045-880d-db968bb6dcfc

This repository also contains a sample Compose Multiplatform app that showcases the components
provided by this library. You can check out this repository and simply run the app, but before you
do that, please make sure to:

 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there
 - run `./gradlew podInstall` in the project root

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :sample:composeApp:assembleDebug`
 - find `.apk` file in `sample/composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :sample:composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `sample/iosApp/iosApp.xcworkspace` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Browser
Run the browser application: `./gradlew :sample:composeApp:jsBrowserDevelopmentRun`

[badge-android]: http://img.shields.io/badge/-android-6EDB8D.svg?style=flat
[badge-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat
[badge-js]: http://img.shields.io/badge/-js-F8DB5D.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg?style=flat

# Adapt
![badge][badge-android] ![badge][badge-ios] ![badge][badge-js] ![badge][badge-wasm] ![badge][badge-jvm]

Adapt is a Compose-based UI component library that provides you components from various commonly
used design systems on different platforms like Material 3 (for Android & Web), Cupertino (for
iOS & macOS), and WinUI 3 (for Windows).

Adapt provides a Flutter-like experience where you can mix-and-match UI components between platforms
to achieve your desired UI/UX output(s) (e.g. using Cupertino components on Android devices and 
Material components on Apple devices).

## Types of components
Adapt offers the following 2 types of composables/components:
1. **Platform-specific** - These look the same on all platforms (e.g. `WindowsButton`, `CupertinoSpinner`)
2. **Platform-agnostic** - These look native-like on every platform (e.g. `AdaptButton`, `AdaptCircularIndicator`)

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
[badge-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?style=flat

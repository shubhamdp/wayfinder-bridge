# wayfinder-bridge
An Android app that forwards Google Maps navigation notifications to external devices. Captures turn-by-turn navigation instructions in real-time and prepares them for seamless forwarding to connected peripherals.

## Features
- Intercepts Google Maps navigation notifications in real-time
- Displays turn-by-turn navigation instructions
- Runs seamlessly in the background
- Clean, minimal interface

## Future Features
- Bluetooth peripheral support and forwarder

## Requirements
- Android 5.0 (API 21) or higher
- Google Maps app installed
- Notification access permission

## Setup
1. Clone the repository:
   ```bash
   git clone git@github.com:shubhamdp/wayfinder-bridge.git
   cd wayfinder-bridge
   ```

2. Build using Gradle:
   ```bash
   # On macOS/Linux
   ./gradlew build

   # On Windows
   gradlew.bat build
   ```

   Or open in Android Studio and build using the IDE.

3. Install on device:
   ```bash
   # Using Gradle
   ./gradlew installDebug

   # Or using ADB directly
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

4. Grant notification access permission when prompted

## Usage
1. Open the app
2. Grant notification access permission if not already done
3. Start navigation in Google Maps
4. The app will display navigation instructions as they appear
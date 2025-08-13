# Android Overlay Checker

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) <!-- Optional: Add a license badge -->

An Android application to help developers and testers inspect the current values of framework resources, especially those affected by Runtime Resource Overlays (RROs) or device configurations.

## Features

*   **Check Resource Values:** Enter the name of any Android resource (e.g., `config_showNavigationBar`, `colorAccent`, `dimen_navigation_bar_height`) to see its current runtime value.
*   **Predefined Quick Checks:** Includes a list of commonly checked resource tags for quick access.
*   **Simple UI:** Clean and straightforward interface for easy use.
*   **Dynamic Results:** Instantly see the value as reported by the Android system on the target device.

## Why use this?

When working with Android theming, device customizations, or Runtime Resource Overlays (RROs), it can be challenging to determine the final resolved value of a resource. This tool allows you to:

*   Verify if your RROs are being applied correctly.
*   Inspect default resource values on different devices or Android versions.
*   Debug theming issues by checking specific color, dimension, boolean, or string resource values.

## Screenshots

| Main Screen                                   | 
| :--------------------------------------------: |
| <img width="1080" height="600" alt="Screenshot_1755116449" src="https://github.com/user-attachments/assets/165931db-fce7-4411-8116-85508ef5fb14" />|


## How to Launch

`adb shell am start -n "com.hpnightowl.resourcecheck/.MainActivity"`
    

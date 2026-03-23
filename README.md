# 📞 Calling App (Android - Kotlin)

## 🚀 Overview

A basic Calling App built using Kotlin that simulates real call flow. It demonstrates UI handling, activity navigation, and proper call state management.

## ✨ Features

* 🔢 Dial Pad with number input (*, # supported)
* ⌫ Backspace / Delete functionality
* 📲 Outgoing Call Screen with "Calling..." status
* 📥 Incoming Call Simulation (Accept / Reject)
* ⏱ Call Timer (starts from 00:00)
* 🔇 Mute toggle (UI)
* 🔊 Speaker toggle (UI)
* 📞 End Call anytime (handled in all states)
* 🎯 Call State Flow:

  * Idle → Calling → Ringing → Active → Ended
* 🎨 Smooth Animations (fade, slide, popup)
* 👆 Button Press Effects (visual feedback)
* 👤 Call Mapping (number display as caller identity)

## ⚙️ Tech Stack

* Kotlin
* Android SDK
* XML UI
* Animations

## 🧠 Approach

The app uses multiple activities to simulate real calling behavior. State transitions are managed using intents and controlled UI logic.

## 📌 Note

This is a UI-based simulation. No real SIM or calling functionality is used.


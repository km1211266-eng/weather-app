# 🌤 Weekly Weather Tracker — IMAD5112 Practicum 2024

## GitHub Repository
🔗 **[https://github.com/YOUR-USERNAME/WeatherApp](https://github.com/YOUR-USERNAME/WeatherApp)**
> ⚠️ Replace `YOUR-USERNAME` with your actual GitHub username before submitting.

---

## App Overview

A native Android app built with **Kotlin** in **Android Studio** for the IMAD5112 Practicum 2024. It allows users to track daily weather data across a week using **parallel arrays**, a **loop-based average calculation**, and **3-screen navigation** via Intents.

---

## Screens

| Screen | Class | Purpose |
|--------|-------|---------|
| Splash Screen | `SplashActivity.kt` | App intro with name, student number, logo, enter & exit buttons |
| Main Screen | `MainActivity.kt` | Enter daily weather data, calculate average, navigate to detail |
| Detail Screen | `DetailActivity.kt` | Display full weekly weather report |

---

## Screenshots

> Take screenshots in the emulator and save them to a `screenshots/` folder in your repo before submitting.

### Screenshot 1 — Splash Screen
![Splash](screenshots/splash.png)

**Explanation:** The splash screen is the first screen the user sees when opening the app. It displays the app name ("Weekly Weather Tracker"), the student's name and number, and two buttons — **Enter App** (navigates to the Main Screen) and **Exit App** (closes the application using `finishAffinity()`).

---

### Screenshot 2 — Main Screen (Empty)
![Main Empty](screenshots/main_empty.png)

**Explanation:** The main screen contains four `EditText` input fields for the day name, minimum temperature, maximum temperature, and weather condition. Data is stored using four parallel `ArrayList` arrays. The **+ Add Day** button validates all inputs before adding data.

---

### Screenshot 3 — Main Screen (Data Added)
![Main Data](screenshots/main_data.png)

**Explanation:** After entering valid data for a day and tapping **+ Add Day**, a Toast message confirms the addition and all input fields are cleared ready for the next day's data. The **Calculate Average** button loops through the `maxTemps` array to compute and display the weekly average maximum temperature.

---

### Screenshot 4 — Detail Screen
![Detail](screenshots/detail.png)

**Explanation:** The detail screen receives all four parallel arrays from `MainActivity` via an `Intent`. It loops through the arrays using `for (i in days.indices)` and builds a formatted report showing each day's minimum temperature, maximum temperature, and weather condition. The **Back** button calls `finish()` to return to the main screen.

---

### Screenshot 5 — Error Handling
![Error](screenshots/error.png)

**Explanation:** If any input field is empty or contains an invalid number, the relevant `EditText` displays an inline error message using `.error = "..."`. The app also validates that the minimum temperature does not exceed the maximum temperature.

---

## Key Code Concepts

### Parallel Arrays
```kotlin
private val days       = ArrayList<String>()
private val minTemps   = ArrayList<Double>()
private val maxTemps   = ArrayList<Double>()
private val conditions = ArrayList<String>()
```

### Average Calculation Loop
```kotlin
var total = 0.0
for (temp in maxTemps) {
    total += temp
}
val average = total / maxTemps.size
```

### Passing Arrays via Intent
```kotlin
intent.putStringArrayListExtra("days", days)
intent.putExtra("minTemps", minTemps.toDoubleArray())
```

### Receiving Arrays in DetailActivity
```kotlin
val days     = intent.getStringArrayListExtra("days")
val minTemps = intent.getDoubleArrayExtra("minTemps")
```

---

## GitHub Actions
Automated build on every push to `main`. See `.github/workflows/android-build.yml`.

---

## How to Run
1. Open Android Studio
2. Clone: `git clone https://github.com/YOUR-USERNAME/WeatherApp.git`
3. Open the project folder
4. Wait for Gradle sync
5. Press **Shift+F10** to run on the emulator

---

## Student Information
- **Module:** IMAD5112
- **Assessment:** Practicum 2024
- **Student Name:** Your Name Here
- **Student Number:** ST10XXXXXX

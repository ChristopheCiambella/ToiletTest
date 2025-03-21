# Android Test Technique – Public Toilets (Sanisette)

This project is an Android application developed as a technical test for displaying public toilets in Paris. The app fetches data from the RATP Open Data API and displays a list of public toilets along with key details.

---

## Table of Contents

- [Objective](#objective)
- [Features](#features)
- [Architecture & Technologies](#architecture--technologies)
- [Installation & Running](#installation--running)
- [Conclusion](#conclusion)

---

## Objective

The goal of this application is to:

- Retrieve a list of public toilets in Paris using the RATP Open Data API (dataset **sanisettesparis2011**).
- Display for each toilet:
    - The full address
    - Opening hours
    - Accessibility for people with reduced mobility (PMR)
    - The distance from the current location (if location permission is granted)
- Allow the user to filter the list to display only PMR-accessible toilets.

---

## Features

### Main Features

- **Toilet List**  
  Display a detailed list showing the address, opening hours, and PMR accessibility of each public toilet.

- **Distance Calculation**  
  Show the distance between the current location and each toilet (if permission is granted).

- **PMR Filter**  
  Provide a switch in the top app bar that lets users toggle a filter to display only toilets accessible for people with reduced mobility.

### Bonus Features

- **Toilet Details Screen**  
  Navigate to a dedicated detail screen when a toilet is selected.

- **Interactive Map**  
  Display toilets on a map with markers.

- **External Navigation**  
  Allow opening the toilet’s address in an external mapping application to display directions.

---

## Architecture & Technologies

### Architecture

This project follows Clean Architecture principles:

- **domain**  
  Contains pure business logic (use cases, models, and abstract interfaces).

- **data**  
  Handles network calls, persistence (DataStore), and mappers.

- **present**  
  Implemented using Jetpack Compose for UI and navigation.

- **app**  
  Contains Android application class and Koin modules declaration to inject dependencies in entire project.

- **design-system-property**  
  Defines UI properties in a simple, platform-agnostic manner

- **design-system**  
  Jetpack Compose components, each corresponding to a `Property` from `design-system-property`


### Technologies Used

- **Kotlin** – Primary programming language.
- **Jetpack Compose** – Declarative UI framework.
- **Navigation Compose** – Manages navigation between screens.
- **Koin** – Dependency injection framework.
- **Retrofit** – Api call network library.
- **Kotlinx Serialization** – Serializes/deserializes JSON for passing complex objects between screens.
- **DataStore** – Persists preferences (e.g., PMR filter).
- **Google Maps Compose** – (Bonus) Displays maps with custom markers.

---

## Installation & Running

### Prerequisites

- Android Studio (Meerkat or later)
- Android SDK 23 or higher
- Internet connection (to access the Open Data API)
- Google Api keys

### Steps

1. **Clone the Repository**

2. **Open the Project in Android Studio**

    - Open Android Studio and choose **File > Open**.
    - Select the project directory.

3. **Add Goole API keys**

    To add your Maps API key to this project:
    - If the secrets.properties file does not exist, create it in the same folder as the local.properties file.
    - Add this line, where YOUR_API_KEY is your API key:

```      
       MAPS_API_KEY=YOUR_API_KEY
`````

4. **Build the Project**

    - Let Gradle download all required dependencies (Koin, Jetpack Compose, etc.).

5. **Run the Application**

    - Choose an emulator or a physical device.
    - Click the **Run** button in Android Studio.

---

## Conclusion

This project meets the technical test requirements by providing a functional, well-architected, and easily extensible Android application. The code demonstrates modern Android development practices, including Clean Architecture, Jetpack Compose, Koin for dependency injection, and more.

If you have any questions or suggestions, please feel free to contact me.

This project was developed as part of a technical test to demonstrate proficiency in modern Android technologies and best practices in application architecture.

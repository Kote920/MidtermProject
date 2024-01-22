Project Title
Midterm Project

Overview
This Android project follows the principles of Android Clean Architecture, utilizing multiple layers for a structured and maintainable codebase. The project includes features like Dagger Hilt for dependency injection, Firebase for authentication and Firestore for data storage, Retrofit for network requests, and other widely-used libraries for UI and data manipulation.

Project Structure
The project is structured based on Clean Architecture principles, consisting of the following layers:

Presentation Layer

Located in the app module.
Uses MVVM architecture with ViewModels and LiveData for managing UI-related data.
Utilizes ViewBinding for efficient view access.
Navigation is implemented using the Android Navigation component.
Core UI components are provided by AppCompat, Material Design, and ConstraintLayout.
Domain Layer

Located in the domain package.
Contains business logic and use case classes.
Decouples business logic from UI and data layers.
Data Layer

Divided into datastore, repository, and datasource packages.
Datastore manages data storage preferences using Android DataStore.
Repository abstracts data sources and provides a clean API for the domain layer.
Datasource implements interfaces from the repository to fetch data from different sources (Firestore, Room, Retrofit).
Dependency Injection

Utilizes Dagger Hilt for dependency injection.
Scopes and components are structured to maintain a clean and modular codebase.
Network Layer

Uses Retrofit for handling network requests.
Moshi is employed for JSON serialization/deserialization.
Persistence Layer

Implements Room database for local data storage.
Room DAOs are used to interact with the SQLite database.
Datastore Preferences is used for managing key-value pairs.
Utilities

Glide library is used for efficient image loading and caching.
Commons Codec is included for additional utility functionalities.
OkHttp is utilized for HTTP client functionality.
Project Dependencies
The project dependencies are managed using the Gradle build system:

Core Android libraries like core-ktx, appcompat, and constraintlayout for UI development.
Firebase libraries for authentication and Firestore.
Dagger Hilt for dependency injection.
Android Navigation component for navigation.
Moshi for JSON parsing.
Retrofit for handling network requests.
Room for local database operations.
Glide for image loading and caching.
Other utility libraries such as okhttp, commons-codec, etc.
Usage
To build and run the project, ensure that you have the necessary dependencies installed and configured. Follow the standard Android development practices for building, testing, and deploying the application.

Notes
Make sure to keep dependencies updated regularly to benefit from the latest features, improvements, and security patches.

Feel free to customize the project structure and dependencies based on your specific requirements and preferences. Ensure that proper documentation is maintained to aid future development and collaboration.

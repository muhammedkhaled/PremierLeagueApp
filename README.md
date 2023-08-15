# Premier league app

simple app consist from one screen built with compose with clean architecture somewhere between MVVM and MVI. we call it a unidirectional data flow architecture. wich Google’s architecture guide recommends it now.

In this repo you'll find:

    1. User Interface built with Jetpack Compose (design for Dark mode only).
    2. layered architecture every feature hase (its own domain and presentation layers), uses cases, events and uiState.
    3. every features decupled and depend on core data layer with interfaces with abstraction.
    4. A data layer with a repository and two data sources (cashing using Room and "https://www.football-data.org" api for remote).    
    5. Reactive UIs using Flow and coroutines for asynchronous operations.
    6. Rxjava flowable for room Reactive updates.

# DAZN Gallery

This app loads images from the JSON file and display the images in grid of 3 items and the details about the images are shown when user clicks on the image.

## Package structure
The app is divided into three main packages
- data : To load the data. It contains all Repository's Impl and ViewModels
- domain : It contains all models and use-cases
- presentation or ui : It contains the UI i.e Activities, Fragments etc.

## Third party libraries Used
- Picasso for Image loading
- Turbine for mocking scopes
- Hilt for dependency Injection
- Gson for parsing json

## Latest trends used in the application
- Kotlin
- Dagger hilt
- MVVM
- Turbine library
- Viewpager2 (Jetpack)

## Other
- MVVM architecture is used with Hilt
- Interface Segregation Principle : ListItemClickListener used only for click.
- Singleton design patterns mainly for injecting repo
- Constraint layouts for optimized UI by enhancing the view hierarchy

# DREAM MOVIES APP - 

This app will retrieve information from the TMDB and display it in various view types by interacting with a REST API from themoviedb.org.


## REST API with MVVM and Retrofit2 with Java.

This app demonstrates the following specifics: 

✔ Communicating with a webservice (rest api) using Retrofit2
✔ MVVM Architecture: ViewModel, Repository, Client structure
✔ How to design an architecture
✔ DayNight Theme Customizations.
✔ Singletons
✔ Custom Loading Animation ProgressBar in Recyclerview
✔ ViewModels and AndroidViewModels
✔ Multiple View Types in a Recyclerview
✔ RecyclerView Pagination
✔ Building Custom Toolbars
✔ Customising Toolbar Behaviours with CoordinatorLayout & AppBarLayout
✔ Observables, LiveData, MutableLiveData and MediatorLiveData
✔ Displaying Images using Glide
✔ CardViews
✔ SearchViews
✔ Menus
✔ Passing data between activities using intent extras
✔ Executors and Background Threads
✔ ThreadPools
✔ Network Security Config for HTTP (API 28+)

* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service
* [Gson](https://github.com/google/gson) which handles the deserialization of the returned JSON to Kotlin data objects
* [Glide](https://bumptech.github.io/glide/) to load and cache images by URL.
* [Material](https://github.com/material-components/material-components-android) To add Custom Android Specific Widgets
* [SimpleRatingBar](https://github.com/FlyingPumba/SimpleRatingBar) A powerful RatingBar alternative to Android's default

It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Executors](https://developer.android.com/reference/java/util/concurrent/Executors)

## Screenshots

![Screenshot 1](screenshots/screen_1.png)
![Screenshot 2](screenshots/screen_2.png)
![Screenshot 3](screenshots/screen_3.png)



## Tc2r's Notes

[Scalar](https://square.github.io/retrofit/) is a library that allows retrofit to return the json result as a String.

Retrofit is a library that creates a network API for an application. Based on the content from a web service

The Network Layer is the Api that the ViewModel will use to communicate with a web service.


The Moshi Library parses Json into Json Objects


**Retrofit has Coroutine Support**

Using the Library "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:" Retrofit is able
to add a CoroutineCallAdapterFactory.

This enables retrofit to produce a Coroutine based Api.
The CoroutineCallAdapterFactory allows the ability to replace the "Call" in the Service methods with Deferred from Coroutines.

Deferred is a type of non-blocking Cancellable job that can directly return a result.

Because Retrofit does everything on a background thread, we can use the UI thread andd dont need any other threads.

When using CoroutineScope/ Jobs in ViewModel, don't forget to cancel them in the OnClear Lifecycle.

Built a bindingAdapter that takes the url from an xml attribute associated with an imageview and use glide to load the image

Parcelling is a way of sharing objects between different processes by flattening objects into a stream of data called a Parcel. 

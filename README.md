# Movie assignment

>> Check the screenshots and GIF image of the app at the bottom of the page

## [Download APK](https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/apk/TvShowAssignment.apk)
## Assignment details
● Create a simple Android app with an initial screen showing a button which when
pressed initiates presenting a new screen which requests a list of tv shows and
presents them to the user.  
● Produce clean and well formatted/documented code following appropriate coding
standards depending on the platform you choose  
● Send a request to the movie api database and parse the list of tv shows  
● For each of the tv shows returned by the API a set of data will be returned, use
the following two fields to populate the list:  
○ name : the name of the tv show  
○ poster_path : An image url for the given tv show. (Note the api only returns  
a path of the image url e.g “/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg”, in
order to create a full url to request the image you must set the base url as
the following “https://image.tmdb.org/t/p/w500/” so the URL for the image
would be  
“https://image.tmdb.org/t/p/w500/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg”  

● You should use a RecyclerView for displaying the list of tv shows  
● Add a button to the screen that will allow the user to sort pressed. The user
should have a few different options when it comes to sorting, e.g alphabetically,
chronologically etc.  
● Bonus points will be given to any extra features implemented.  

## Feature Implemented
- Lazy Loading
- Pull to refresh
- Filter TvShow by Name, air date, vote count, popularity etc
- DIFF Utils to ease loading
- Storing in room database
- Unit test cases
- UI test cases for database Implementation

## Architechure and lib used
- MVVM
- Data Binding
- Coroutines
- Clean Architecture
- Dagger Hilt for Dependency Injection
- Room Database For persistency

## Unit Testing And Instrumentation Testing
- Written Database test cases
- Written View Model test cases
- Written Few UI test cases

## Libraries used
- Coroutines - for multi threading
- Databinding and view model for MVVM
- Junit and mockito for Unit testing
- DIffUtils to make recyclerview adapter load items effeciently when refresh Meteor


## How to Run the application
- Download the APK link given above or download the project and open in Android Studio and Sync and click on run on any Emulator or Device
- Click on the MovieAssignment in device dashboard
- It will load the Top Tv Shows first page
- There are 2 tabs Top TvShows and Favourite TvShows
- Click on the TvSHow to see the details screen
- Click Add to Favourite or remove 
- Scroll doan the list to load more items
- Click on the filter FAB icon at the bottom right
- It will display filter TvShows by Name, Weight, Date etc


## Here is the screen shot and Gif image for the application
<table>
<tr>
<td>
  <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss1.png" width="130" height="250" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss2.png" width="130" height="250"/> 
</td>
  <td>
  <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss3.png" width="130" height="250" />
 </td>
  <td>
  <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss4.png" width="130" height="250" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss5.png" width="130" height="250"/> 
</td>
  <td>
  <img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/ss6.png" width="130" height="250" />
 </td>
</tr>
</table> 

## GIF image
<img src="https://github.com/sibaprasad12/TvShowAssigmnent/blob/main/app/images/movieAssignment.gif" width="250" height="500" />

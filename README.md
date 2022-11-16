# xkcdViewer
Two Screen Kotlin Android Application utilizing the public api for [xkcd comics](https://xkcd.com/) available [here](https://xkcd.com/info.0.json) to create a simple comic strip viewer. 

## Implementation
The limitations of the the public API means I cannot query a range of entries or chain requests like what may be possible with more sophisticated API's. The only lookups available are comicById and comicByLatest.

Screen 1: SingleComicViewFragment to display a single comic at a time, fresco for image loading. This view has a navigation bar to allow users to move in a limited fashion through the comic history, lite logic to ensure the id never goes out of bounds

Screen 2: SingleComicInfoFragment to display a single comics complete metadata without an image, including title, alt text, and transcript, release date

### Dependencies
* GSON:       Serialize/Deserialize
* Retrofit:   Type-Safe Http Client
* Coroutune:  Kotlin Threading
* Freso:      Image Loading and Placeholders
* Koin:       Kotlin Dependency Injection

### Retrospective
The nature of the material available on this API was comics, and that had some implications I was ready to deal with, and some I was not.
* I was hoping resizing would be apart of Fresco alas I couldn't figure it out. One comic in eight looks adequate. A big reason I was eager to try Fresco was for image resizing, but the only option I found inside of its Documentations was for jpegs only and the comic links to all pngs. Part of my attempts to mitigate this issue was giving up even more of the view for the comic image, hiding all the meta data on the additional screen.
* I did attempt to see how quickly I could request the entire comic catalouge, my first attempts took nearly five minutes. Upon Introduction of parcelize and that time dropped to under 4 minutes. Must faster, but still not fast enough to attempt to do it secretly.

### Stretch goals
* Introduce image resizing. Most comics are neigh unreadable. I can change the measurements to look better for some and worse for others. I'd like every comic to be clear. Maybe a scrollview would allow even the images that have gone off screen to be fully viewable
* Cache comic history into Room database. Comics are static objects, mr. Randall has no cause to go backwards in his comic history and make updates, I would have liked to store the entire comic catalouges metadata in a local database and request images as needed. This would minimize repeat requests, and would allow me to introduce features I would have liked to have on the API available on my DAO, ie. comicsByIdRange, comicsByTitle, comicsByYear, comicsByMonth, comicsByDay, etc..

#### Disclaimers and Licenses
I did not write or contribute in any meaningful way to the production or distribution any xkcd comic(s) by Randall Munroe, they are licensed under the [Creative Commons Attribution-NonCommerical 2.5 License](https://xkcd.com/license.html) for noncommerical reuse and copy. 
